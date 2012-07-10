<%@ page import="com.worthsoln.utils.LegacySpringUtils" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>

<html:xhtml/>

<p class="header">Change Password</p>

<logic:present name="firstLogon">
  <bean:message key="firstlogn.message" /><br /><br />
</logic:present>

<logic:present name="error">
  <bean:message key="errors.header"/><bean:message key="errors.prefix"/><bean:message key="oldpassword.incorrect" /><bean:message key="errors.suffix"/><bean:message key="errors.footer"/>
</logic:present>

<bean:message key="passwordstrength.message" /><br /><br />

<html:errors/>

<html:form action="/patient/passwordChange">

  <table cellpadding="3" >

    <tr>
      <td><b>Username</b></td>
      <td><%= LegacySpringUtils.getSecurityUserManager().getLoggedInUsername() %></td>
    </tr>

    <tr>
      <td><b>Current Password</b></td>
      <td><html:password property="oldpassword" /></td>
    </tr>

    <tr>
      <td><b>New Password</b></td>
      <%--<td><html:password property="passwordPwd" /></td>--%>
        <td><input type="password" id="passwordPwd" name="passwordPwd" autocomplete="off" onkeyup="chkPass(this.value);" /></td>
                      <td>
                        <div id="scorebarBorder">
                        <div id="score">0%</div>
                        <div id="scorebar">&nbsp;</div>
                        </div>
                    </td><td><div id="complexity">Too Short</div></td>
    </tr>

    <tr>
      <td><b>Repeat New Password</b></td>
      <td><html:password property="newpasswordagain" /></td>
    </tr>

    <tr>
      <td><html:submit value="Submit" styleClass="formButton"/></td>
    </tr>

  </table>


  <p class="header">Password Strength Scores</p>

  <table id="tablePwdStatus" cellpadding="5" cellspacing="1" border="0" class="pwdmeter">
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
<table id="tablePwdNotes" cellpadding="5" cellspacing="1" border="0" class="pwdmeter">
     <tr class="pwdmeter">
         <th class="pwdmeter">Quick Footnotes</th>
     </tr>
     <tr class="pwdmeter">
         <td class="pwdmeter">
             &bull; <strong>Flat:</strong> Rates that add/remove in non-changing increments.<br />
             &bull; <strong>Incr:</strong> Rates that add/remove in adjusting increments.<br />
             &bull; <strong>Cond:</strong> Rates that add/remove depending on additional factors.<br />
             &bull; <strong>Comp:</strong> Rates that are too complex to summarize. See source code for details.<br />
             &bull; <strong>n:</strong> Refers to the total number of occurrences.<br />
             &bull; <strong>len:</strong> Refers to the total password length.<br />
             &bull; Additional bonus scores are given for increased character variety.<br />
             &bull; Final score is a cumulative result of all bonuses minus deductions.<br />
             &bull; Final score is capped with a minimum of 0 and a maximum of 100.<br />
             &bull; Score and Complexity ratings are not conditional on meeting minimum requirements.<br />
         </td>
     </tr>
     <tr class="pwdmeter">
         <th class="pwdmeter">DISCLAIMER</th>
     </tr>
     <tr class="pwdmeter">
         <td class="pwdmeter">
             <p>This application is designed to assess the strength of password strings.  The instantaneous visual feedback provides the user a means to improve the strength of their passwords, with a hard focus on breaking the typical bad habits of faulty password formulation.  Since no official weighting system exists, we created our own formulas to assess the overall strength of a given password.  Please note, that this application does not utilize the typical "days-to-crack" approach for strength determination.  We have found that particular system to be severely lacking and unreliable for real-world scenarios.  This application is neither perfect nor foolproof, and should only be utilized as a loose guide in determining methods for improving the password creation process. </p>
         </td>
     </tr>
 </table>
    

</html:form>

