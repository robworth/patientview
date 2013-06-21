package org.patientview.radar.model.sequenced;

import java.util.Date;

public class Therapy extends SequencedModel {

    private Date treatmentRecordDate;
    private Boolean nsaid, nsaidPrior;
    private Boolean diuretic, diureticPrior;
    private Boolean antihypertensive, antihypertensivePrior;

    private Boolean aceInhibitor, aceInhibitorPrior;
    private Boolean arb1Antagonist, arb1AntagonistPrior;
    private Boolean calciumChannelBlocker, calciumChannelBlockerPrior;
    private Boolean betaBlocker, betaBlockerPrior;
    private Boolean otherAntihypertensive, otherAntihypertensivePrior;

    private Boolean insulin, insulinPrior;
    private Boolean lipidLoweringAgent, lipidLoweringAgentPrior;
    private Boolean epo, epoPrior;

    private String other1, other2, other3, other4;
    private String other1Prior, other2Prior, other3Prior, other4Prior;

    public Date getTreatmentRecordDate() {
        return treatmentRecordDate;
    }

    public void setTreatmentRecordDate(Date treatmentRecordDate) {
        this.treatmentRecordDate = treatmentRecordDate;
    }

    public Boolean getNsaid() {
        return nsaid;
    }

    public void setNsaid(Boolean nsaid) {
        this.nsaid = nsaid;
    }

    public Boolean getNsaidPrior() {
        return nsaidPrior;
    }

    public void setNsaidPrior(Boolean nsaidPrior) {
        this.nsaidPrior = nsaidPrior;
    }

    public Boolean getDiuretic() {
        return diuretic;
    }

    public void setDiuretic(Boolean diuretic) {
        this.diuretic = diuretic;
    }

    public Boolean getDiureticPrior() {
        return diureticPrior;
    }

    public void setDiureticPrior(Boolean diureticPrior) {
        this.diureticPrior = diureticPrior;
    }

    public Boolean getAntihypertensive() {
        return antihypertensive;
    }

    public void setAntihypertensive(Boolean antihypertensive) {
        this.antihypertensive = antihypertensive;
    }

    public Boolean getAntihypertensivePrior() {
        return antihypertensivePrior;
    }

    public void setAntihypertensivePrior(Boolean antihypertensivePrior) {
        this.antihypertensivePrior = antihypertensivePrior;
    }

    public Boolean getAceInhibitor() {
        return aceInhibitor;
    }

    public void setAceInhibitor(Boolean aceInhibitor) {
        this.aceInhibitor = aceInhibitor;
    }

    public Boolean getAceInhibitorPrior() {
        return aceInhibitorPrior;
    }

    public void setAceInhibitorPrior(Boolean aceInhibitorPrior) {
        this.aceInhibitorPrior = aceInhibitorPrior;
    }

    public Boolean getArb1Antagonist() {
        return arb1Antagonist;
    }

    public void setArb1Antagonist(Boolean arb1Antagonist) {
        this.arb1Antagonist = arb1Antagonist;
    }

    public Boolean getArb1AntagonistPrior() {
        return arb1AntagonistPrior;
    }

    public void setArb1AntagonistPrior(Boolean arb1AntagonistPrior) {
        this.arb1AntagonistPrior = arb1AntagonistPrior;
    }

    public Boolean getCalciumChannelBlocker() {
        return calciumChannelBlocker;
    }

    public void setCalciumChannelBlocker(Boolean calciumChannelBlocker) {
        this.calciumChannelBlocker = calciumChannelBlocker;
    }

    public Boolean getCalciumChannelBlockerPrior() {
        return calciumChannelBlockerPrior;
    }

    public void setCalciumChannelBlockerPrior(Boolean calciumChannelBlockerPrior) {
        this.calciumChannelBlockerPrior = calciumChannelBlockerPrior;
    }

    public Boolean getBetaBlocker() {
        return betaBlocker;
    }

    public void setBetaBlocker(Boolean betaBlocker) {
        this.betaBlocker = betaBlocker;
    }

    public Boolean getBetaBlockerPrior() {
        return betaBlockerPrior;
    }

    public void setBetaBlockerPrior(Boolean betaBlockerPrior) {
        this.betaBlockerPrior = betaBlockerPrior;
    }

    public Boolean getOtherAntihypertensive() {
        return otherAntihypertensive;
    }

    public void setOtherAntihypertensive(Boolean otherAntihypertensive) {
        this.otherAntihypertensive = otherAntihypertensive;
    }

    public Boolean getOtherAntihypertensivePrior() {
        return otherAntihypertensivePrior;
    }

    public void setOtherAntihypertensivePrior(Boolean otherAntihypertensivePrior) {
        this.otherAntihypertensivePrior = otherAntihypertensivePrior;
    }

    public Boolean getInsulin() {
        return insulin;
    }

    public void setInsulin(Boolean insulin) {
        this.insulin = insulin;
    }

    public Boolean getInsulinPrior() {
        return insulinPrior;
    }

    public void setInsulinPrior(Boolean insulinPrior) {
        this.insulinPrior = insulinPrior;
    }

    public Boolean getLipidLoweringAgent() {
        return lipidLoweringAgent;
    }

    public void setLipidLoweringAgent(Boolean lipidLoweringAgent) {
        this.lipidLoweringAgent = lipidLoweringAgent;
    }

    public Boolean getLipidLoweringAgentPrior() {
        return lipidLoweringAgentPrior;
    }

    public void setLipidLoweringAgentPrior(Boolean lipidLoweringAgentPrior) {
        this.lipidLoweringAgentPrior = lipidLoweringAgentPrior;
    }

    public Boolean getEpo() {
        return epo;
    }

    public void setEpo(Boolean epo) {
        this.epo = epo;
    }

    public Boolean getEpoPrior() {
        return epoPrior;
    }

    public void setEpoPrior(Boolean epoPrior) {
        this.epoPrior = epoPrior;
    }

    public String getOther1() {
        return other1;
    }

    public void setOther1(String other1) {
        this.other1 = other1;
    }

    public String getOther2() {
        return other2;
    }

    public void setOther2(String other2) {
        this.other2 = other2;
    }

    public String getOther3() {
        return other3;
    }

    public void setOther3(String other3) {
        this.other3 = other3;
    }

    public String getOther4() {
        return other4;
    }

    public void setOther4(String other4) {
        this.other4 = other4;
    }

    public String getOther1Prior() {
        return other1Prior;
    }

    public void setOther1Prior(String other1Prior) {
        this.other1Prior = other1Prior;
    }

    public String getOther2Prior() {
        return other2Prior;
    }

    public void setOther2Prior(String other2Prior) {
        this.other2Prior = other2Prior;
    }

    public String getOther3Prior() {
        return other3Prior;
    }

    public void setOther3Prior(String other3Prior) {
        this.other3Prior = other3Prior;
    }

    public String getOther4Prior() {
        return other4Prior;
    }

    public void setOther4Prior(String other4Prior) {
        this.other4Prior = other4Prior;
    }
}
