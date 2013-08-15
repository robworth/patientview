package org.patientview.radar.model.sequenced;

import org.patientview.radar.model.BaseModel;

import java.util.Date;

public class LabData extends SequencedModel {

    public enum UrineVolumeCondition {
        NORMAL(0), ANURIA(1), OLIGURIA(2), POLYURIA(3);
        private int id;

        UrineVolumeCondition(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    public enum Haematuria {
        NOT_TESTED(9), MICROSCOPIC(1), MACROSCOPIC(2), NEGATIVE(3);
        private int id;

        Haematuria(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public String getLabel() {
            return BaseModel.getLabelFromEnum(toString());
        }
    }

    public enum Proteinuria {
        NEGATIVE(0), TRACE(9), ONE_PLUS(1), TWO_PLUS(2), THREE_PLUS(3), FOUR_PLUS(4);
        private int id;

        Proteinuria(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public String getLabel() {
            String label = toString();
            label = label.replace("_PLUS", "+");
            label = label.replace("_", " ");
            String[] parts = label.split(" ");
            label = "";
            for (String part : parts) {
                String formatted = part.toLowerCase();
                formatted = Character.toUpperCase(formatted.charAt(0)) + formatted.substring(1);
                label += formatted;
                label += " ";
            }
            return label;
        }
    }

    public enum Albuminuria {
        NEGATIVE(0), ONE_PLUS(1), TWO_PLUS(2), THREE_PLUS(3), FOUR_PLUS(4);
        private int id;

        Albuminuria(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
    }

    public enum Present {
        PRESENT(1), NOT_PRESENT(0), NOT_DONE(9);
        private int id;

        Present(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public String getLabel() {
            return BaseModel.getLabelFromEnum(toString());
        }
    }

    public enum Anca {
        C_PR3(1, "C(PR3)"), P_MPO(2, "P(MPO)"), C_P(3, "C+P"), CANCA(4, "CANCA"), PANCA(5, "PANCA"),
        NEGATIVE(6, "Negative"), NOT_DONE(9, "Not done");
        private int id;
        String label;

        Anca(int id, String label) {
            this.id = id;
            this.label = label;
        }

        public int getId() {
            return id;
        }

        public String getLabel() {
            return label;
        }

        public void setLabel(String label) {
            this.label = label;
        }
    }

    public enum PositiveNegativeNotDone {
        NEGATIVE(0), POSITIVE(1), NOT_DONE(9);
        private int id;

        PositiveNegativeNotDone(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public String getLabel() {
            return BaseModel.getLabelFromEnum(toString());
        }
    }

    public enum PositiveNegativeUnknown {
        POSITIVE(1), NEGATIVE(0), UNKNOWN(9);
        private int id;

        PositiveNegativeUnknown(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }

        public String getLabel() {
            return BaseModel.getLabelFromEnum(toString());
        }
    }

    /**
     * An interesting one - apparently the antibodies found in the blood.
     * http://kidshealth.org/parent/system/medical/test_immunoglobulins.html
     */
    public enum Immunoglobulins {
        NEGATIVE(1, "Neg"), IGG_POS(2, "IgG Pos"), IGM_POS(3, "IgM Pos"), IGG_IGM_POS(4, "IgG + IgM Pos"),
        NOT_DONE(9, "Not done");
        private int id;
        private String label;

        Immunoglobulins(int id, String label) {
            this.id = id;
            this.label = label;
        }

        public int getId() {
            return id;
        }

        public String getLabel() {
            return label;
        }
    }

    private Date date;

    // Blood fields
    private Double hb, wbc, neutrophils, platelets, sodium, potassium, bun, serumCreatinine, protein, albumin, crp;
    private Double totalCholesterol, hdlCholesterol, ldlCholesterol;
    private Double triglycerides, thyroxine, tsh, phosphate, ferritin, inr;

    // Urinalysis - dipstick
    private UrineVolumeCondition urineVolumeCondition;
    private Haematuria haematuria;
    private Proteinuria proteinuria;
    private Albuminuria albuminuria;
    private Boolean leucocytesUrine, nitrite, glucose;

    // Urinalysis - Lab
    private Double urineVolume, proteinCreatinineRatio, albuminCreatinineRatio, osmolality;
    private Boolean bacteria;
    private Present dysmorphicErythrocytes, redCellCast, whiteCellCasts;

    // Creatinine Clearance
    private Double creatinineClearance;

    // Antibodies & Infections
    private Anca anca;
    private PositiveNegativeNotDone ena, ana, antiDsDna, cryoglobulins, antiGbm;
    private String dnaAntibodies;
    private Double igG, igA, igM, complementC3, complementC4;
    private String complementOther;
    private Boolean complementOtherSelected;
    private PositiveNegativeUnknown c3NephriticFactor;
    private Double antiClqAntibodies, antistreptolysin;
    private PositiveNegativeUnknown hepatitisB, hepatitisC, hivAntibody;
    private Boolean dnaTakenFactorH;
    private Immunoglobulins ebv, cmvSerology;
    private Boolean cmvSymptomatic;
    private PositiveNegativeNotDone parvovirusAntibody;

    private Boolean otherInfection;
    private String otherInfectionDetail;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getHb() {
        return hb;
    }

    public void setHb(Double hb) {
        this.hb = hb;
    }

    public Double getWbc() {
        return wbc;
    }

    public void setWbc(Double wbc) {
        this.wbc = wbc;
    }

    public Double getNeutrophils() {
        return neutrophils;
    }

    public void setNeutrophils(Double neutrophils) {
        this.neutrophils = neutrophils;
    }

    public Double getPlatelets() {
        return platelets;
    }

    public void setPlatelets(Double platelets) {
        this.platelets = platelets;
    }

    public Double getSodium() {
        return sodium;
    }

    public void setSodium(Double sodium) {
        this.sodium = sodium;
    }

    public Double getPotassium() {
        return potassium;
    }

    public void setPotassium(Double potassium) {
        this.potassium = potassium;
    }

    public Double getBun() {
        return bun;
    }

    public void setBun(Double bun) {
        this.bun = bun;
    }

    public Double getSerumCreatinine() {
        return serumCreatinine;
    }

    public void setSerumCreatinine(Double serumCreatinine) {
        this.serumCreatinine = serumCreatinine;
    }

    public Double getProtein() {
        return protein;
    }

    public void setProtein(Double protein) {
        this.protein = protein;
    }

    public Double getAlbumin() {
        return albumin;
    }

    public void setAlbumin(Double albumin) {
        this.albumin = albumin;
    }

    public Double getCrp() {
        return crp;
    }

    public void setCrp(Double crp) {
        this.crp = crp;
    }

    public Double getTotalCholesterol() {
        return totalCholesterol;
    }

    public void setTotalCholesterol(Double totalCholesterol) {
        this.totalCholesterol = totalCholesterol;
    }

    public Double getHdlCholesterol() {
        return hdlCholesterol;
    }

    public void setHdlCholesterol(Double hdlCholesterol) {
        this.hdlCholesterol = hdlCholesterol;
    }

    public Double getLdlCholesterol() {
        return ldlCholesterol;
    }

    public void setLdlCholesterol(Double ldlCholesterol) {
        this.ldlCholesterol = ldlCholesterol;
    }

    public Double getTriglycerides() {
        return triglycerides;
    }

    public void setTriglycerides(Double triglycerides) {
        this.triglycerides = triglycerides;
    }

    public Double getThyroxine() {
        return thyroxine;
    }

    public void setThyroxine(Double thyroxine) {
        this.thyroxine = thyroxine;
    }

    public Double getTsh() {
        return tsh;
    }

    public void setTsh(Double tsh) {
        this.tsh = tsh;
    }

    public Double getPhosphate() {
        return phosphate;
    }

    public void setPhosphate(Double phosphate) {
        this.phosphate = phosphate;
    }

    public Double getFerritin() {
        return ferritin;
    }

    public void setFerritin(Double ferritin) {
        this.ferritin = ferritin;
    }

    public Double getInr() {
        return inr;
    }

    public void setInr(Double inr) {
        this.inr = inr;
    }

    public UrineVolumeCondition getUrineVolumeCondition() {
        return urineVolumeCondition;
    }

    public void setUrineVolumeCondition(UrineVolumeCondition urineVolumeCondition) {
        this.urineVolumeCondition = urineVolumeCondition;
    }

    public Haematuria getHaematuria() {
        return haematuria;
    }

    public void setHaematuria(Haematuria haematuria) {
        this.haematuria = haematuria;
    }

    public Proteinuria getProteinuria() {
        return proteinuria;
    }

    public void setProteinuria(Proteinuria proteinuria) {
        this.proteinuria = proteinuria;
    }

    public Albuminuria getAlbuminuria() {
        return albuminuria;
    }

    public void setAlbuminuria(Albuminuria albuminuria) {
        this.albuminuria = albuminuria;
    }

    public Boolean getLeucocytesUrine() {
        return leucocytesUrine;
    }

    public void setLeucocytesUrine(Boolean leucocytesUrine) {
        this.leucocytesUrine = leucocytesUrine;
    }

    public Boolean getNitrite() {
        return nitrite;
    }

    public void setNitrite(Boolean nitrite) {
        this.nitrite = nitrite;
    }

    public Boolean getGlucose() {
        return glucose;
    }

    public void setGlucose(Boolean glucose) {
        this.glucose = glucose;
    }

    public Double getUrineVolume() {
        return urineVolume;
    }

    public void setUrineVolume(Double urineVolume) {
        this.urineVolume = urineVolume;
    }

    public Double getProteinCreatinineRatio() {
        return proteinCreatinineRatio;
    }

    public void setProteinCreatinineRatio(Double proteinCreatinineRatio) {
        this.proteinCreatinineRatio = proteinCreatinineRatio;
    }

    public Double getAlbuminCreatinineRatio() {
        return albuminCreatinineRatio;
    }

    public void setAlbuminCreatinineRatio(Double albuminCreatinineRatio) {
        this.albuminCreatinineRatio = albuminCreatinineRatio;
    }

    public Double getOsmolality() {
        return osmolality;
    }

    public void setOsmolality(Double osmolality) {
        this.osmolality = osmolality;
    }

    public Boolean getBacteria() {
        return bacteria;
    }

    public void setBacteria(Boolean bacteria) {
        this.bacteria = bacteria;
    }

    public Present getDysmorphicErythrocytes() {
        return dysmorphicErythrocytes;
    }

    public void setDysmorphicErythrocytes(Present dysmorphicErythrocytes) {
        this.dysmorphicErythrocytes = dysmorphicErythrocytes;
    }

    public Present getRedCellCast() {
        return redCellCast;
    }

    public void setRedCellCast(Present redCellCast) {
        this.redCellCast = redCellCast;
    }

    public Present getWhiteCellCasts() {
        return whiteCellCasts;
    }

    public void setWhiteCellCasts(Present whiteCellCasts) {
        this.whiteCellCasts = whiteCellCasts;
    }

    public Double getCreatinineClearance() {
        return creatinineClearance;
    }

    public void setCreatinineClearance(Double creatinineClearance) {
        this.creatinineClearance = creatinineClearance;
    }

    public Anca getAnca() {
        return anca;
    }

    public void setAnca(Anca anca) {
        this.anca = anca;
    }

    public PositiveNegativeNotDone getEna() {
        return ena;
    }

    public void setEna(PositiveNegativeNotDone ena) {
        this.ena = ena;
    }

    public PositiveNegativeNotDone getAna() {
        return ana;
    }

    public void setAna(PositiveNegativeNotDone ana) {
        this.ana = ana;
    }

    public PositiveNegativeNotDone getAntiDsDna() {
        return antiDsDna;
    }

    public void setAntiDsDna(PositiveNegativeNotDone antiDsDna) {
        this.antiDsDna = antiDsDna;
    }

    public PositiveNegativeNotDone getCryoglobulins() {
        return cryoglobulins;
    }

    public void setCryoglobulins(PositiveNegativeNotDone cryoglobulins) {
        this.cryoglobulins = cryoglobulins;
    }

    public PositiveNegativeNotDone getAntiGbm() {
        return antiGbm;
    }

    public void setAntiGbm(PositiveNegativeNotDone antiGbm) {
        this.antiGbm = antiGbm;
    }

    public String getDnaAntibodies() {
        return dnaAntibodies;
    }

    public void setDnaAntibodies(String dnaAntibodies) {
        this.dnaAntibodies = dnaAntibodies;
    }

    public Double getIgG() {
        return igG;
    }

    public void setIgG(Double igG) {
        this.igG = igG;
    }

    public Double getIgA() {
        return igA;
    }

    public void setIgA(Double igA) {
        this.igA = igA;
    }

    public Double getIgM() {
        return igM;
    }

    public void setIgM(Double igM) {
        this.igM = igM;
    }

    public Double getComplementC3() {
        return complementC3;
    }

    public void setComplementC3(Double complementC3) {
        this.complementC3 = complementC3;
    }

    public Double getComplementC4() {
        return complementC4;
    }

    public void setComplementC4(Double complementC4) {
        this.complementC4 = complementC4;
    }


    public PositiveNegativeUnknown getC3NephriticFactor() {
        return c3NephriticFactor;
    }

    public void setC3NephriticFactor(PositiveNegativeUnknown c3NephriticFactor) {
        this.c3NephriticFactor = c3NephriticFactor;
    }

    public Double getAntiClqAntibodies() {
        return antiClqAntibodies;
    }

    public void setAntiClqAntibodies(Double antiClqAntibodies) {
        this.antiClqAntibodies = antiClqAntibodies;
    }

    public Double getAntistreptolysin() {
        return antistreptolysin;
    }

    public void setAntistreptolysin(Double antistreptolysin) {
        this.antistreptolysin = antistreptolysin;
    }

    public PositiveNegativeUnknown getHepatitisB() {
        return hepatitisB;
    }

    public void setHepatitisB(PositiveNegativeUnknown hepatitisB) {
        this.hepatitisB = hepatitisB;
    }

    public PositiveNegativeUnknown getHepatitisC() {
        return hepatitisC;
    }

    public void setHepatitisC(PositiveNegativeUnknown hepatitisC) {
        this.hepatitisC = hepatitisC;
    }

    public PositiveNegativeUnknown getHivAntibody() {
        return hivAntibody;
    }

    public void setHivAntibody(PositiveNegativeUnknown hivAntibody) {
        this.hivAntibody = hivAntibody;
    }

    public Boolean getDnaTakenFactorH() {
        return dnaTakenFactorH;
    }

    public void setDnaTakenFactorH(Boolean dnaTakenFactorH) {
        this.dnaTakenFactorH = dnaTakenFactorH;
    }

    public Immunoglobulins getEbv() {
        return ebv;
    }

    public void setEbv(Immunoglobulins ebv) {
        this.ebv = ebv;
    }

    public Immunoglobulins getCmvSerology() {
        return cmvSerology;
    }

    public void setCmvSerology(Immunoglobulins cmvSerology) {
        this.cmvSerology = cmvSerology;
    }

    public Boolean getCmvSymptomatic() {
        return cmvSymptomatic;
    }

    public void setCmvSymptomatic(Boolean cmvSymptomatic) {
        this.cmvSymptomatic = cmvSymptomatic;
    }

    public PositiveNegativeNotDone getParvovirusAntibody() {
        return parvovirusAntibody;
    }

    public void setParvovirusAntibody(PositiveNegativeNotDone parvovirusAntibody) {
        this.parvovirusAntibody = parvovirusAntibody;
    }

    public Boolean getOtherInfection() {
        return otherInfection;
    }

    public void setOtherInfection(Boolean otherInfection) {
        this.otherInfection = otherInfection;
    }

    public String getOtherInfectionDetail() {
        return otherInfectionDetail;
    }

    public void setOtherInfectionDetail(String otherInfectionDetail) {
        this.otherInfectionDetail = otherInfectionDetail;
    }

    public String getComplementOther() {
        return complementOther;
    }

    public void setComplementOther(String complementOther) {
        this.complementOther = complementOther;
    }

    public Boolean getComplementOtherSelected() {
        return complementOtherSelected;
    }

    public void setComplementOtherSelected(Boolean complementOtherSelected) {
        this.complementOtherSelected = complementOtherSelected;
    }
}
