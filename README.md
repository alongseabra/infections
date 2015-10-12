bplist00Ñ_WebMainResourceÕ	
_WebResourceData_WebResourceMIMEType_WebResourceTextEncodingName_WebResourceFrameName^WebResourceURLO0â<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
  <meta http-equiv="Content-Style-Type" content="text/css">
  <title></title>
  <meta name="Generator" content="Cocoa HTML Writer">
  <meta name="CocoaVersion" content="1344.72">
  <style type="text/css">
    p.p1 {margin: 0.0px 0.0px 0.0px 0.0px; font: 13.0px Courier; -webkit-text-stroke: #000000}
    p.p2 {margin: 0.0px 0.0px 0.0px 0.0px; font: 13.0px Courier; -webkit-text-stroke: #000000; min-height: 16.0px}
    span.s1 {font-kerning: none}
  </style>
</head>
<body>
<p class="p1"><span class="s1"># Anson's Khan Academy Project!</span></p>
<p class="p1"><span class="s1">##Usage Instructions</span></p>
<p class="p1"><span class="s1">I've emailed you guys JAR files for both the simulator and the tests. Both can be run from the command line.</span></p>
<p class="p1"><span class="s1">Just navigate to the directory you saved them, then use<span class="Apple-converted-space">Â </span></span></p>
<p class="p1"><span class="s1">&lt;code&gt;java -jar filename.jar&lt;/code&gt; to run them.</span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">You can also just use the files in this repository and build them in your own IDE, though you may have to alter the file paths (using the exact path on your machine) of the graph files in the code to build them on your machine.</span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">Speaking of graph files, if you'd like to input your own, all you have to do is make list of edges, one on each line.</span></p>
<p class="p1"><span class="s1">For example, the relationship A coaches B would be represented by "A" "B". See the text files above if you need more examples.</span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">In case you're wondering what the graphs I've included actually look like, here are some drawing of them I drew up while I was working on the problem: &lt;a href=""&gt;6 users&lt;/a&gt;, &lt;a href=""&gt;18 users&lt;/a&gt;, &lt;a href=""&gt;51 users&lt;/a&gt;.</span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">##General approach</span></p>
<p class="p1"><span class="s1">In my interpretation, users of Khan Academy have two attributes, a list of &lt;i&gt;teachers&lt;/i&gt; (those they are "coached by") and &lt;i&gt;students&lt;/i&gt; (those they are "coaching").<span class="Apple-converted-space">Â </span></span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">For representing the graph of users, I chose a &lt;b&gt;directed acyclic graph&lt;/b&gt;. There were two reasons for this.</span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">The "coaches" relation indicates a transfer of something from one user to another, which in this case I interpreted as knowledge. I thought that a directed graph was well-suited to describe this relationship.<span class="Apple-converted-space">Â </span></span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">Regarding the acyclic nature of the graph, I felt that it was reasonable to assume that one teacher would not later be taught by someone that he/she coached. For example, if Jane coaches John, and John coaches Jim, Jim cannot coach Jane, creating a cycle. In this way, I chose to think of users "lower" on the graph as being slightly less capable and thus in need of coaching.</span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">##Total Infect</span></p>
<p class="p1"><span class="s1">The total infection algorithm is relatively straightforward. Given a user, it will infect the user and iterate through all of its connections (students and teachers), infecting their students and teachers in the same manner. This will continue until the entire connected component is infected. Note that in this way the infection algorithm essentially treats the graph as undirected.</span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">##Limited Infect</span></p>
<p class="p1"><span class="s1">Here's where things get interesting. My original approach to this algorithm actually involved counting the number of distinct connected components in the graph, and then infecting those components in some order until I was within some threshold of the number given to infect.</span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">However, I discovered a few things.</span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">Firstly, I did some research on social network graphs. While perhaps not completely similar to Khan Academy's user graph (it is not a social network, after all), I assume there may be some similarities, especially at scale. The main thing I found with sample data that I could find online (see &lt;a href="https://snap.stanford.edu/data/egonets-Facebook.html"&gt;here&lt;/a&gt; and &lt;a href="https://snap.stanford.edu/data/com-LiveJournal.html") is that most of these graphs contain a single connected component that encompasses most, if not all of the graph. What this means is that the method described above will in most cases be horribly inaccurate, since infecting a user will essentially wipe out the whole graph or close to it every time.</span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">How did I remedy this? In two ways.</span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">Firstly, for this algorithm, I decided it would be helpful if our infection only transmitted via the "coaches" relation. If teachers can only transmit the version to students (different from total infect), then we don't have to worry about the entire graph lighting up if we infect the wrong user.</span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">Next, I decided to place more value on the &lt;i&gt;accuracy&lt;/i&gt; of the infection count than having users completely insulated from the wrong version of the site. In other words, I cared more about the accuracy of the number of users infected than I did on whether or not the entire graph was completely insulated from seeing a mismatched version of the site. With these assumptions in mind, let's move on to the algorithm itself.</span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">Since the graph is &lt;b&gt;directed&lt;/b&gt; and &lt;b&gt;acyclic&lt;/b&gt;, this means that it can be &lt;i&gt;topologically sorted&lt;/i&gt;. This puts users who have the shortest "coaching chain" underneath them at the bottom of the sorted graph. Why is this helpful? Well, if, for example, a user is not coaching anyone, and the infection only transmits via the "coaching" relation, then infecting this user will not infect anyone but itself. This is useful because we can infect relatively few users at a time, starting from the bottom, building our way up the graph, as opposed to starting at the top of the graph and having a long coaching chain potentially cascade the infection down to the bottom (for some reason I kept thinking of the game Plinko when running the scenario in my mind). Infecting close to the fewest number of users we can each time helps us get "ease" our way towards exact number of users we want to infect.</span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">&lt;i&gt;But Anson,&lt;/i&gt; you may be thinking, &lt;i&gt;if we infect users like that aren't we going to get a lot of confused classrooms?&lt;/i&gt; That's right, which is why we can't just infect users in the order they are topologically sorted.<span class="Apple-converted-space">Â </span></span></p>
<p class="p1"><span class="s1">To minimize confusion, we need to infect not only the user, but also all of its &lt;i&gt;teachers&lt;/i&gt; as well (&lt;i&gt;all&lt;/i&gt; teachers because we can't be sure which one teaches the rest of this particular user's class). What this will do is ensure that its classmates also are infected (which minimizes version mismatch), due to the "top down" transmission of the virus.</span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">With this in mind, the general algorithm is as follows:&lt;br&gt;</span></p>
<p class="p1"><span class="s1">1) Topologically sort the user graph.&lt;br&gt;</span></p>
<p class="p1"><span class="s1">2) Find the next uninfected user with the next smallest coaching chain under it.&lt;br&gt;</span></p>
<p class="p1"><span class="s1">3) Infect this user and all of its teachers.&lt;br&gt;</span></p>
<p class="p1"><span class="s1">4) Count the number of infections. If they are within a certain threshold of the desired infection count, or they exceed it, quit. Otherwise, repeat 2-4.&lt;br&gt;</span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">Something to note is that this is not an exact science. There is often more than one way to topologically sort a graph, and my implementation does not take this into account.<span class="Apple-converted-space">Â </span></span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">##Exact Infect</span></p>
<p class="p1"><span class="s1">This was my choice for the optional enhancement. Again, as with the limited infection I interpreted "infection" as transmitting via the "coaches" relation only. Before the real work starts, the algorithm runs a limited infect trial to see if we can "get lucky" and get an exact infection that way. Otherwise, it simply infects every node of the graph in every possible order, checking between each infection to see if the exact count has been reached, and failing if all orderings of infections fail. This will take a very, very, &lt;i&gt;very&lt;/i&gt; long time on almost any graph.</span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">##Improvements</span></p>
<p class="p1"><span class="s1">Firstly, I'd like to know more about how the Khan Academy user graph really looks at scale. For one, if it is more of a series of disconnected, reasonably-sized classrooms, an algorithm for counting connected components would've worked better for limited infection. Also, I'd just like to see it. I bet it's sweet.</span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">If I had more time I would've written more tests. My method seems to work pretty well, but comprehensive analysis and testing could've made this a lot more clear. I also would've liked to make a visualization.</span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">Finally, I'd like to find a way to optimize both the limited and exact infect algorithms. For the exact algorithm, I'd like to look into things like the "clustering component" of a user, its "connectedness", and some other stuff I ran across while researching social network graphs that I really didn't have time to sink my teeth into. As for the limited infection, there's probably more optimization that can be done on top of just picking the next user in topological order, and if I'd had more time I would've looked into this more. I also would like to find the optimal topological order, since there is more than one.</span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">##Final Thoughts</span></p>
<p class="p1"><span class="s1">What an engaging problem! I can't stop thinking about large graphs after doing it. Seriously. I was at the grocery store today and all I could think about was how insane Khan Academy's, or any large website's user graph, must look.<span class="Apple-converted-space">Â </span></span></p>
<p class="p1"><span class="s1">I also found my self thinking a lot about the scalability of my solutions during this project as well, something I don't do a lot at school.</span></p>
<p class="p2"><span class="s1"></span><br></p>
<p class="p1"><span class="s1">Overall, I had a great time thinking through this project. Thank you guys for taking the time to read through all of this! Cheers!</span></p>
</body>
</html>
Ytext/htmlUutf-8P_file:///index.html    ( : P n … ”1z1„1Š1‹                           1 