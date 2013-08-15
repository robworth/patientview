<%@ page import="org.patientview.utils.LegacySpringUtils" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<%--
  ~ PatientView
  ~
  ~ Copyright (c) Worth Solutions Limited 2004-2013
  ~
  ~ This file is part of PatientView.
  ~
  ~ PatientView is free software: you can redistribute it and/or modify it under the terms of the
  ~ GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
  ~ or (at your option) any later version.
  ~ PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
  ~ the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
  ~ GNU General Public License for more details.
  ~ You should have received a copy of the GNU General Public License along with PatientView in a file
  ~ titled COPYING. If not, see <http://www.gnu.org/licenses/>.
  ~
  ~ @package PatientView
  ~ @link http://www.patientview.org
  ~ @author PatientView <info@patientview.org>
  ~ @copyright Copyright (c) 2004-2013, Worth Solutions Limited
  ~ @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
  --%>

<html:xhtml/>
<div class="span9">
<div class="page-header">
    <h1>Change Password</h1>
</div>

<logic:present name="firstLogon">
  <bean:message key="firstlogn.message" /><br /><br />
</logic:present>

<logic:present name="error">
  <bean:message key="errors.header"/><bean:message key="errors.prefix"/><bean:message key="oldpassword.incorrect" /><bean:message key="errors.suffix"/><bean:message key="errors.footer"/>
</logic:present>

<bean:message key="passwordstrength.message" /><br /><br />

<html:errors/>

<html:form action="/control/passwordChange" styleClass="form-horizontal">
    <fieldset>
    <div class="control-group">
        <label class="control-label">Username</label>
        <div class="controls"><%= LegacySpringUtils.getSecurityUserManager().getLoggedInUsername() %></div>
    </div>

    <div class="control-group">
      <label class="control-label">Current Password</label>
      <div class="controls"><html:password property="oldpassword" /></div>
    </div>

    <div class="control-group">
        <label class="control-label">New Password</label>
      <%--<td><html:password property="newpassword" onkeyup="chkPass(this.value);"/></td>
--%>
        <div class="controls"><input type="password" id="passwordPwd" name="passwordPwd" autocomplete="off" onkeyup="chkPass(this.value);" />
            <p class="help-block">
                <span id="scorebarBorder">
                    <span id="score">0%</span>
                    <span id="scorebar">&nbsp;</span>
                </span>
                <span id="complexity">Too Short</span>
            </p>
        </div>
    </div>

    <div class="control-group">
      <label class="control-label">Repeat New Password</label>
      <div class="controls "><html:password property="newpasswordagain" /></div>
    </div>

    <div class="form-actions">
      <td><html:submit value="Submit" styleClass="btn btn-primary"/></td>
    </div>

</html:form>


    <h2 class="header">Password Strength Scores</h2>

    <table id="tablePwdStatus" cellpadding="5" cellspacing="1" border="0" class="table table-bordered table-striped">
        <tr>
            <th colspan="2" class="pwdmeter">Additions</th>
            <th class="pwdmeter">Type</th>
            <th class="pwdmeter">Rate</th>
            <th class="pwdmeter">Count</th>
            <th class="pwdmeter">Bonus</th>
        </tr>
        <tr>
            <td width="1%" class="pwdmeter"><div id="div_nLength" class="fail">&nbsp;</div></td>
            <td width="94%" class="pwdmeter">Number of Characters</td>
            <td width="1%" class="txtCenter pwdmeter ">Flat</td>
            <td width="1%" class="txtCenter italic pwdmeter">+(n*4)</td>
            <td width="1%" class="pwdmeter"><div id="nLength" class="box">&nbsp;</div></td>
            <td width="1%" class="pwdmeter"><div id="nLengthBonus" class="boxPlus">&nbsp;</div></td>
        </tr>
        <tr>
            <td class="pwdmeter"><div id="div_nAlphaUC" class="fail">&nbsp;</div></td>
            <td class="pwdmeter">Uppercase Letters</td>
            <td class="txtCenter pwdmeter">Cond/Incr</td>
            <td nowrap="nowrap" class="txtCenter italic pwdmeter">+((len-n)*2)</td>
           <td class="pwdmeter"><div id="nAlphaUC" class="box">&nbsp;</div></td>
            <td class="pwdmeter"><div id="nAlphaUCBonus" class="boxPlus">&nbsp;</div></td>
        </tr>
        <tr>
            <td class="pwdmeter"><div id="div_nAlphaLC" class="fail">&nbsp;</div></td>
            <td class="pwdmeter">Lowercase Letters</td>
            <td class="txtCenter pwdmeter">Cond/Incr</td>
            <td class="txtCenter italic pwdmeter">+((len-n)*2)</td>
            <td class="pwdmeter"><div id="nAlphaLC" class="box">&nbsp;</div></td>
            <td class="pwdmeter"><div id="nAlphaLCBonus" class="boxPlus">&nbsp;</div></td>
        </tr>
        <tr>
            <td class="pwdmeter"><div id="div_nNumber" class="fail">&nbsp;</div></td>
            <td class="pwdmeter">Numbers</td>
            <td class="txtCenter pwdmeter">Cond</td>
            <td class="txtCenter italic pwdmeter">+(n*4)</td>
            <td class="pwdmeter"><div id="nNumber" class="box">&nbsp;</div></td>
            <td class="pwdmeter"><div id="nNumberBonus" class="boxPlus">&nbsp;</div></td>
       </tr>
        <tr>
            <td class="pwdmeter"><div id="div_nSymbol" class="fail">&nbsp;</div></td>
            <td class="pwdmeter">Symbols</td>
            <td class="txtCenter pwdmeter">Flat</td>
            <td class="txtCenter italic pwdmeter">+(n*6)</td>
            <td class="pwdmeter"><div id="nSymbol" class="box">&nbsp;</div></td>
            <td class="pwdmeter"><div id="nSymbolBonus" class="boxPlus">&nbsp;</div></td>
       </tr>
        <tr>
            <td class="pwdmeter"><div id="div_nMidChar" class="fail">&nbsp;</div></td>
            <td class="pwdmeter">Middle Numbers or Symbols</td>
            <td class="txtCenter pwdmeter">Flat</td>
            <td class="txtCenter italic pwdmeter">+(n*2)</td>
            <td class="pwdmeter"><div id="nMidChar" class="box">&nbsp;</div></td>
            <td class="pwdmeter"><div id="nMidCharBonus" class="boxPlus">&nbsp;</div></td>
       </tr>
        <tr>
            <td class="pwdmeter"><div id="div_nRequirements" class="fail">&nbsp;</div></td>
            <td class="pwdmeter">Requirements</td>
            <td class="txtCenter pwdmeter">Flat</td>
            <td class="txtCenter italic pwdmeter">+(n*2)</td>
            <td class="pwdmeter"><div id="nRequirements" class="box">&nbsp;</div></td>
            <td class="pwdmeter"><div id="nRequirementsBonus" class="boxPlus">&nbsp;</div></td>
       </tr>
        <tr>
            <th colspan="6" class="pwdmeter">Deductions</th>
        </tr>
        <tr>
            <td width="1%" class="pwdmeter"><div id="div_nAlphasOnly" class="pass">&nbsp;</div></td>
            <td width="94%" class="pwdmeter">Letters Only</td>
            <td width="1%" class="txtCenter pwdmeter">Flat</td>
            <td width="1%" class="txtCenter italic pwdmeter">-n</td>
            <td width="1%" class="pwdmeter"><div id="nAlphasOnly" class="box">&nbsp;</div></td>
            <td width="1%" class="pwdmeter"><div id="nAlphasOnlyBonus" class="boxMinus">&nbsp;</div></td>
        </tr>
        <tr>
            <td class="pwdmeter"><div id="div_nNumbersOnly" class="pass">&nbsp;</div></td>
            <td class="pwdmeter">Numbers Only</td>
            <td class="txtCenter pwdmeter">Flat</td>
            <td class="txtCenter italic pwdmeter">-n</td>
            <td class="pwdmeter"><div id="nNumbersOnly" class="box">&nbsp;</div></td>
            <td class="pwdmeter"><div id="nNumbersOnlyBonus" class="boxMinus">&nbsp;</div></td>
        </tr>
        <tr>
            <td class="pwdmeter"><div id="div_nRepChar" class="pass">&nbsp;</div></td>
            <td class="pwdmeter">Repeat Characters (Case Insensitive)</td>
            <td class="txtCenter pwdmeter">Comp</td>
            <td nowrap="nowrap" class="txtCenter italic pwdmeter"> - </td>
            <td class="pwdmeter"><div id="nRepChar" class="box">&nbsp;</div></td>
            <td class="pwdmeter"><div id="nRepCharBonus" class="boxMinus">&nbsp;</div></td>
        </tr>
        <tr>
            <td class="pwdmeter"><div id="div_nConsecAlphaUC" class="pass">&nbsp;</div></td>
            <td class="pwdmeter">Consecutive Uppercase Letters</td>
            <td class="txtCenter pwdmeter">Flat</td>
            <td class="txtCenter italic pwdmeter">-(n*2)</td>
            <td class="pwdmeter"><div id="nConsecAlphaUC" class="box">&nbsp;</div></td>
            <td class="pwdmeter"><div id="nConsecAlphaUCBonus" class="boxMinus">&nbsp;</div></td>
        </tr>
        <tr>
            <td class="pwdmeter"><div id="div_nConsecAlphaLC" class="pass">&nbsp;</div></td>
            <td class="pwdmeter">Consecutive Lowercase Letters</td>
            <td class="txtCenter pwdmeter">Flat</td>
            <td class="txtCenter italic pwdmeter">-(n*2)</td>
            <td class="pwdmeter"><div id="nConsecAlphaLC" class="box">&nbsp;</div></td>
            <td class="pwdmeter"><div id="nConsecAlphaLCBonus" class="boxMinus">&nbsp;</div></td>
        </tr>
        <tr>
            <td class="pwdmeter"><div id="div_nConsecNumber" class="pass">&nbsp;</div></td>
            <td class="pwdmeter">Consecutive Numbers</td>
            <td class="txtCenter pwdmeter">Flat</td>
            <td class="txtCenter italic pwdmeter">-(n*2)</td>
            <td class="pwdmeter"><div id="nConsecNumber" class="box">&nbsp;</div></td>
            <td class="pwdmeter"><div id="nConsecNumberBonus" class="boxMinus">&nbsp;</div></td>
        </tr>
        <tr>
            <td class="pwdmeter"><div id="div_nSeqAlpha" class="pass">&nbsp;</div></td>
            <td class="pwdmeter">Sequential Letters (3+)</td>
            <td class="txtCenter pwdmeter">Flat</td>
            <td class="txtCenter italic pwdmeter">-(n*3)</td>
            <td class="pwdmeter"><div id="nSeqAlpha" class="box">&nbsp;</div></td>
            <td class="pwdmeter"><div id="nSeqAlphaBonus" class="boxMinus">&nbsp;</div></td>
        </tr>
        <tr>
            <td class="pwdmeter"><div id="div_nSeqNumber" class="pass">&nbsp;</div></td>
            <td class="pwdmeter">Sequential Numbers (3+)</td>
            <td class="txtCenter pwdmeter">Flat</td>
            <td class="txtCenter italic pwdmeter">-(n*3)</td>
            <td class="pwdmeter"><div id="nSeqNumber" class="box">&nbsp;</div></td>
            <td class="pwdmeter"><div id="nSeqNumberBonus" class="boxMinus">&nbsp;</div></td>
        </tr>
        <tr>
            <td class="pwdmeter"><div id="div_nSeqSymbol" class="pass">&nbsp;</div></td>
            <td class="pwdmeter">Sequential Symbols (3+)</td>
            <td class="txtCenter pwdmeter">Flat</td>
            <td class="txtCenter italic pwdmeter">-(n*3)</td>
            <td class="pwdmeter"><div id="nSeqSymbol" class="box">&nbsp;</div></td>
            <td class="pwdmeter"><div id="nSeqSymbolBonus" class="boxMinus">&nbsp;</div></td>
        </tr>
        <tr>
            <th colspan="6"  class="pwdmeter">Legend</th>
        </tr>
        <tr class="pwdmeter">
            <td colspan="6" class="pwdmeter">
                <ul id="listLegend">
                    <li><div class="exceed imgLegend">&nbsp;</div> <span class="bold">Exceptional:</span> Exceeds minimum standards. Additional bonuses are applied.</li>
                    <li><div class="pass imgLegend">&nbsp;</div> <span class="bold">Sufficient:</span> Meets minimum standards. Additional bonuses are applied.</li>
                    <li><div class="warn imgLegend">&nbsp;</div> <span class="bold">Warning:</span> Advisory against employing bad practices. Overall score is reduced.</li>
                    <li><div class="fail imgLegend">&nbsp;</div> <span class="bold">Failure:</span> Does not meet the minimum standards. Overall score is reduced.</li>
                </ul>
            </td>
        </tr>
    </table>


   <h2 class="pwdmeter">Quick Footnotes</h2>
   <ul class="pwdmeter">
       <li><strong>Flat:</strong> Rates that add/remove in non-changing increments.</li>
       <li><strong>Incr:</strong> Rates that add/remove in adjusting increments.</li>
       <li><strong>Cond:</strong> Rates that add/remove depending on additional factors.</li>
       <li><strong>Comp:</strong> Rates that are too complex to summarize. See source code for details.</li>
       <li><strong>n:</strong> Refers to the total number of occurrences.</li>
       <li><strong>len:</strong> Refers to the total password length.</li>
       <li>Additional bonus scores are given for increased character variety.</li>
       <li>Final score is a cumulative result of all bonuses minus deductions.</li>
       <li>Final score is capped with a minimum of 0 and a maximum of 100.</li>
       <li>Score and Complexity ratings are not conditional on meeting minimum requirements.</li>
   </ul>

   <h2>Disclaimer</h2>

   <p>This application is designed to assess the strength of password strings.  The instantaneous visual feedback
       provides the user a means to improve the strength of their passwords, with a hard focus on breaking the typical
       bad habits of faulty password formulation.  Since no official weighting system exists, we created our own
       formulas to assess the overall strength of a given password.  Please note, that this application does not
       utilize the typical "days-to-crack" approach for strength determination.  We have found that particular system
       to be severely lacking and unreliable for real-world scenarios.  This application is neither perfect nor
       foolproof, and should only be utilized as a loose guide in determining methods for improving the password
       creation process.
   </p>

</div>

