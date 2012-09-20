<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib prefix="logic" uri="http://struts.apache.org/tags-logic" %>

<div class="row">
        <div class="span12 footer">
            <ul class="barSpeperatedNav">
                <logic:present tenancy="rpv">
                    <li><a href="/disclaimer.do">Disclaimer</a></li>
                </logic:present>
            </ul>
        </div>
    </div>
</div>

<script type="text/javascript" src="/js/bootstrap.js"></script>
<script type="text/javascript" src="/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="/ibd/js/raphael-min.js"></script>
<script type="text/javascript" src="/ibd/js/ico.min.js"></script>
<script type="text/javascript" src="/ibd/js/ibd.js"></script>
<script type="text/javascript" src="/js/pwdmeter.js"></script>

<%
    // todo tracking code should only be present on live...
%>
<logic:present tenancy="rpv">
    <script type="text/javascript">
    var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
    document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
    </script>
    <script type="text/javascript">
    try {
    var pageTracker = _gat._getTracker("UA-12523245-4");
    pageTracker._trackPageview();
    } catch(err) {}</script>
</logic:present>
<logic:present tenancy="ibd">
    <script type="text/javascript">

        var _gaq = _gaq || [];
        _gaq.push(['_setAccount', 'UA-12523245-11']);
        _gaq.push(['_trackPageview']);

        (function() {
            var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
            ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
            var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
        })();

    </script>
</logic:present>

</body>
