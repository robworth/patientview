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

<script type="text/javascript" src="/js/jquery-1.7.2.min.js"></script>
<script type="text/javascript" src="/js/bootstrap.js"></script>
<script type="text/javascript" src="/js/bootstrap-datepicker.js"></script>
<script type="text/javascript" src="/ibd/js/raphael-min.js"></script>
<script type="text/javascript" src="/ibd/js/ico.min.js"></script>
<script type="text/javascript" src="/ibd/js/ibd.js"></script>
<script type="text/javascript" src="/js/pwdmeter.js"></script>


<script type="text/javascript">
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</script>
<script type="text/javascript">
try {
var pageTracker = _gat._getTracker("UA-12523245-4");
pageTracker._trackPageview();
} catch(err) {}</script>

</body>
