<%@ Page Title="" Language="VB" MasterPageFile="~/MasterPage.master" AutoEventWireup="false" CodeFile="clinician-srns.aspx.vb" Inherits="clinician" %>

<%@ Register assembly="AjaxControlToolkit" namespace="AjaxControlToolkit" tagprefix="asp" %>

<asp:Content ID="Content1" ContentPlaceHolderID="head" Runat="Server">
</asp:Content>
<asp:Content ID="Content2" ContentPlaceHolderID="ContentPlaceHolder1" Runat="Server">
    <h2>
        Information for Clinicians - Steroid Resistance Nephrotic Syndrome</h2>
        <div id="information" style="margin-left:20px;">
            <p style="font-weight: 700"> <asp:Image ID="Image1" runat="server" /> &nbsp;RADAR
            </p>
            <asp:Panel ID="Panel1" runat="server" Width="90%">
           <p style="font-weight: 700">   RaDaR (A National Registry of Rare Kidney Disease)
</p> 

                <p>
                    In recent years there has been much interest in the care of Rare Diseases, and 
                    it has received significant backing. In 2009 the EU called for the development 
                    of strategies for the treatment of rare disease. In the UK, Rare Disease UK 
                    (www.raredisease.org.uk) has been developed, principally by the Genetic Interest 
                    Group and partners, with a clear mandate to develop a coherent strategy for 
                    prevention, diagnosis, treatment, research into and information sharing about, 
                    rare diseases. Furthermore, the MRC with NIHR developed the ‘Patient Research 
                    Cohort Initiative’, funding projects facilitating development of cohorts for the 
                    purpose of research.
                </p>
                <p>
                    One of the projects to receive funding from the MRC was RaDaR. This rare kidney 
                    disease registry has the backing of the Renal Association and the BAPN and is 
                    designed to develop cohorts of patients with rare renal disease to facilitate 
                    translational and epidemiological research into rare kidney diseases by setting 
                    up and maintaining comprehensive clinical databases in partnership with 
                    disease-specific research groups.
                </p>
                <p>
                    This initiative is now underway. The two pilot projects are, studies in Steroid 
                    Resistant Nephrotic Syndrome in Childhood (Bristol) and MPGN (Birmingham). The 
                    data will be collected on this web-based database under the remit of RaDaR which 
                    is managed by the UK Renal Registry and governed by the Renal Association.
                </p>
                <p>
                    The five year plan for RaDaR is to complete the two pilot projects with 
                    presentation of the data/results but also to encourage further studies. 
                    Individuals or Centres will be encouraged to form a disease specific research 
                    group, present a proposal for a project to the RaDaR committee and develop 
                    specific data collection fields for the database.
                </p>

            </asp:Panel>
            <asp:CollapsiblePanelExtender ID="Panel1_CollapsiblePanelExtender" 
                runat="server" Enabled="True" TargetControlID="Panel1" ExpandedImage="~/images/collapse.jpg" CollapsedImage="~/images/expand.jpg" CollapsedSize="0"  ImageControlID="Image1" ExpandControlID="Image1" CollapseControlID="Image1" Collapsed="true">
            </asp:CollapsiblePanelExtender>
           <p style="font-weight: 700"><asp:Image ID="Image2" runat="server" /> &nbsp;SRNS Study</p>
            <asp:Panel ID="Panel2" runat="server" Width="90%">
           <p style="font-weight: 700"> The SRNS study </p>

                <p>
                    A study to correlate the epidemiological and clinical features of Steroid 
                    Resistance Nephrotic Syndrome including FSGS (Focal Segmental 
                    GlomeruloSclerosis) in childhood, in the UK, with genotype and to develop 
                    biomarkers of disease activity post transplantation.
                </p>
                <p>
                    Initially the project was designed to focus on FSGS, however, in order to be 
                    more inclusive and with the advent of a European Registry of Nephrotic Syndrome 
                    it seemed prudent to encompass all steroid resistant nephrotic syndrome at this 
                    time which might allow for easier collaboration at a later date.
                </p>
                <p>
                    Rather than a leading hypothesis, the central aims of this study are:</p>
                <ul>
                    <li>
                        <p>
                            To identify and develop a cohort of children in the UK with SRNS and to perform 
                            gene analysis of the genes currently known to cause nephrotic syndrome in 
                            childhood.</p>
                    </li>
                    <li>
                        <p>
                            To provide a comprehensive genotype/phenotype correlation of the disease, in a 
                            substantial cohort of patients.</p>
                    </li>
                    <li>
                        <p>
                            To answer the question of whether and to what degree post-transplant recurrence 
                            of FSGS is a heterogeneous disease, seen at the level of the cellular response.</p>
                    </li>
                    <li>
                        <p>
                            To provide the basis for a predictive in vitro test for patients who will suffer 
                            recurrence of the disease post-transplantation
                        </p>
                    </li>
                </ul>
                <p>
                    &nbsp;The project will utilise data collected by RaDaR on every child in the UK with 
                    SRNS and analyse it for patterns, particularly clinical parameters on diagnosis 
                    that may predict future outcome. Each patient consented will be tested for the 
                    gene mutations currently known to cause FSGS in childhood (these are described 
                    in detail under the gene analysis heading). Those patients who undergo 
                    transplantation will have blood and urine samples taken pre and post operatively 
                    to look for biomarkers of disease and their correlation to disease recurrence 
                    post transplantation. Finally, for those patients who suffer disease recurrence 
                    post transplantation and undergo plasma exchange, samples of plasma will be 
                    taken whilst in disease recurrence and then again in remission to compare for 
                    the presence of biomarkers of disease. The gene analysis data will be fed back 
                    to the lead clinician for the patient. The results for the other components of 
                    the study will be assimilated and presented in meetings or peer reviewed 
                    journals.
                </p>

            </asp:Panel> 
            <asp:CollapsiblePanelExtender ID="Panel2_CollapsiblePanelExtender" 
                runat="server" Enabled="True" TargetControlID="Panel2" ExpandedImage="~/images/collapse.jpg" CollapsedImage="~/images/expand.jpg" CollapsedSize="0"  ImageControlID="Image2" ExpandControlID="Image2" CollapseControlID="Image2" Collapsed="true">
            </asp:CollapsiblePanelExtender>
          
    <p style="font-weight: 700"><asp:Image ID="Image3" runat="server" />
        &nbsp;DSWG</p>
        <asp:Panel ID="Panel3" runat="server" Width="90%">
            <p style="font-weight: 700">
                The Working Group
            </p>
            <p>
                Professor Moin Saleem is the chief investigator for the study with Dr Mark 
                    Taylor and Dr David Ansell significant contributors. The data collected by the 
                    local teams in each of the 13 paediatric renal units in the UK will be analysed 
                    by the research group with the UK Renal Registry and a statistician at the 
                    University of Bristol. The tissue samples will be analysed by the research group 
                    in the Academic Renal Unit (Department of Clinical Sciences at North Bristol) of 
                    the University of Bristol. This unit is based in the Southmead Hospital Campus 
                    in a new building, which also houses the UK Renal Registry.
            </p>
            <p>
                The research group includes Hugh McCarthy a paediatric nephrology trainee 
                    undertaking an MD who is very happy to help with any difficulties/questions 
                    encountered (H.McCarthy@bristol.ac.uk ).</p>
        
        </asp:Panel>
            <asp:CollapsiblePanelExtender ID="Panel3_CollapsiblePanelExtender" 
                runat="server" Enabled="True" TargetControlID="Panel3" ExpandedImage="~/images/collapse.jpg" CollapsedImage="~/images/expand.jpg" CollapsedSize="0"  ImageControlID="Image3" ExpandControlID="Image3" CollapseControlID="Image3" Collapsed="true">
            </asp:CollapsiblePanelExtender>
    <p style="font-weight: 700">
        <asp:Image ID="Image4" runat="server" />  
        &nbsp;The Gene Analysis</p>
            <asp:Panel ID="Panel4" runat="server" Width="90%">
            <p style="font-weight: 700">Gene Analysis

</p>
                <p>
                    The purpose of this component of the project is to identify, in a large 
                    unselected cohort, the prevalence of gene mutations that may be associated with 
                    childhood SRNS. Although there are seven genes described in the patient 
                    information sheets that have so far been identified, it is envisaged that there 
                    will be others that are identified later. The consent has been specifically 
                    written so that the samples can be utilised at a later date to test for any new 
                    genes.
                </p>
                <p>
                    The gene analysis will be carried out in the Academic Renal Unit in Bristol. DNA 
                    will be extracted from the patient’s blood sample and undergo PCR for each exon 
                    in each gene. These will then be sent away for sequencing to identify mutations. 
                    It may be that in the future the research group will collaborate with others or 
                    employ other techniques to speed up the process.
                </p>
                <p>
                    It is expected that approximately 20-25% of sporadic cases of childhood FSGS 
                    will have an identifiable gene mutation with the majority of these podocin 
                    mutations. However in SRNS as a whole it is expected that this number may be 
                    less in those above the age of two at onset. However this is an exciting 
                    opportunity to test a large cohort of patients to tell us more about prevalence 
                    of these gene abnormalities in the UK population. It also affords the 
                    opportunity to look for heterozygous sequence variants and digenic inheritance 
                    (2 separate genes affected) in greater detail.
                </p>
                <p><b>Nephrin (NPHS1)</b>
                    <br />
                    Located on locus 19q31. It spans 26kb and has 29 exons. It was first discovered 
                    by Kestila in 1998 during the investigation of Finnish type congenital nephrotic 
                    syndrome [1]. It is a trans-membrane protein of the immunoglobulin family of 
                    cell adhesion molecules found exclusively in the podocyte. It is a major player 
                    in the slit diaphragm with multiple functions including organisation of the 
                    cytoskeleton, signalling, determining podocyte polarity and podocyte 
                    development. Although mostly recognised in its association with CNS, Nephrin 
                    mutations have been found in older children with SRNS. Initially in tri-allelic 
                    hits [2], in 2008 a French group described mutations in 10 children with SRNS in 
                    whom Podocin mutations were negative. All were steroid resistant and had a 
                    protracted course, averaging 13.6years from disease onset to end stage renal 
                    failure [3]. 
</p>
                <p>
                    1. Kestila M et al. 1998. Molecular Cell 1(4); 575-582.<br />
                    2. Koziell A et al 2002. Human Molecular Genetics 11(4); 379-388.<br />
                    3. Philippe A et al 2008. J Am Soc Nephrol 19 (10); 1871-1878
                </p>
                <p>
                    <b>Podocin (NPHS2)</b>
                    <br />
                    Located on locus 1q25-q31. It has 8 exons. Boute first described this gene in 
                    2000 using positional cloning in families with AR childhood FSGS [1]. In fact 
                    this has proved to be the most common gene causing FSGS, both familial and 
                    sporadic. Podocin is a podocyte specific protein which interacts with nephrin 
                    (and CD2AP) to enhance its signalling and mutations affect Podocin and Nephrin 
                    recruitment to the slit diaphragm. Typically children with this mutation show no 
                    response to immunosuppression and were thought not to suffer disease recurrence 
                    post transplantation. However, there are now reported cases of disease 
                    recurrence [2]. There is some suggestion that risk of recurrence correlates to 
                    type of mutation [3], but a lot more information is needed before these 
                    questions can be definitively answered.
</p>
                <p>
                    1. Boute et al 2000. Nature Genetics. 24(4); 349-354.<br />
                    2. Bertelli et al 2003. Am J Kidney Dis. 41(6); 1314-1321.
                    <br />
                    3. Weber et al 2005. Transplantation. 80(1S); 128-134.
                </p>
                <p><b>PLCε1 (or NPHS3)</b>
                    <br />
                    Located on locus 10q23. It spans 334.4kb and has 34 exons. Not exclusive to the 
                    glomeruli it is involved in intracellular signalling and regulating Calcium 
                    influx. It was identified as a causative mutation in early onset SRNS in 2006 by 
                    positional cloning of both sporadic and non-sporadic forms. In those with 
                    truncating mutations phenotype was DMS, in those with non-truncating mutations, 
                    phenotype was FSGS [1]. A small proportion of the cohort appeared to respond to 
                    immunosuppression, and this needs to be tested in a larger population. In the 
                    podocyte PLCe1 is located in the podocyte cell bodies and the foot processes. It 
                    appears to interact with nephrin and the slit diaphragm proteins. In its 
                    absence, nephrin and podocin are down-regulated and development is impeded. 
                    However it is worth noting that in recent study, testing a large cohort of 
                    patients (adults and children) with FSGS, it was not identified [2].
</p>
                <p>
                    1. Hinkes et al. 2006 Nature Genetics 38(12); 1397-1405.
                    <br />
                    2. Gbadegesin et al 2009. Ped Neph 24(2); 281-285.
                </p>
                <p>
                    <b>CD2AP</b>
                    <br />
                    Located on locus 6p12. It has 17 exons. It is found in all organs except the 
                    brain. CD2AP is closely linked in the slit diaphragm with the other proteins, 
                    nephrin and podocin and connects to the actin cytoskeleton. It was identified as 
                    a causative gene mutation in two adults with FSGS in 2003 and a child with 
                    infantile FSGS in 2007 [1, 2]. More recently 3 children with sporadic FSGS and 
                    heterozygous mutations have been reported with associated down-regulation of 
                    slit diaphragm proteins [3]. CD2AP is clearly involved in the slit diaphragm and 
                    there are animal models which have severe renal phenotypes, however human 
                    phenotypes are not well described.
</p>
                <p>
                    1. Kim et al. 2003. Science 300(5623); 1298-1300.
                    <br />
                    2. Lowik et al 2007. Kidney International 72; 1198-1203.
                    <br />
                    3. Gigante et al 2009. Nephrol Dial Transplant 24(6); 1858-1864.
                </p>
                <p>
                    <b>αActinin 4 </b>
                    <br />
                    Located on locus 19q13. It has ....exons. An actin-bundling protein, which is 
                    expressed widely, but especially in the podocyte where it interacts with the 
                    other slit diaphragm proteins and is involved with maintaining cytoskeleton and 
                    also cell motility and adhesion. Using positional cloning it was first 
                    identified in three families with autosomal dominant FSGS with adolescent or 
                    adult onset [1]. More recently it has been identified in sporadic FSGS and 
                    childhood onset familial FSGS [2, 3].
</p>
                <p>
                    1. Kaplin et al. 2000. Nature Genetics 24(3); 251-256.
                    <br />
                    2. Weins et al. 2005. J Am Soc Nephrol 16; 3694-3701.
                    <br />
                    3. Dai et al. 2009. Nephron Clin Pract 111(2); 87-94.
                </p>
                <p>
                    <b>WT1</b>
                    <br />
                    Located on locus 11p13. It spans 50kb and has 10 exons. It is a transcription 
                    factor (affecting expression of genes in the cell nucleus) that was identified 
                    as a candidate gene for the Wilms Tumour by positional cloning and is known also 
                    to cause Denys Drash Syndrome and Frasier Syndrome with associated Diffuse 
                    Mesangial Sclerosis and FSGS respectively. In the latter syndrome, phenotypic 
                    females may be genotypically male (male pseudohermaphroditism), so it is 
                    important to karyotype all females with FSGS. Interestingly, it has also been 
                    shown to cause sporadic, isolated childhood SRNS (FSGS on histology) with an 
                    incidence of nearly 4% in previously tested cohorts [1, 2]. This includes girls 
                    with chromosomes 46XX. Typically the associated phenotype is of early onset, 
                    steroid resistance, rapid progression to ESRD but no recurrence post transplant 
                    [3].
</p>
                <p>
                    1. Mucha et al 2006. Ped Res 59(2); 325-331.
                    <br />
                    2. Aucella et al 2006. Ped Neph 21; 1393-1398.
                    <br />
                    3. Cho et al 2008. Ped Neph 23(1); 63-70.
                </p>
                <p>
                    <b>TRPC6</b>
                    <br />
                    Located on locus 11q21-q22. It has 13 exons. It is a member of the transient 
                    receptor potential super family and is a calcium ion channel which associates 
                    with the slit diaphragm proteins and interacts directly with nephrin and 
                    podocin. Mutations are often gain of function nephrin mutations cause an up 
                    regulation of expression. TRPC6 is also a mechanosensor and may translate 
                    mechanical tension to ion channel action [1]. In 1999, analysis of a large 
                    kindred in New Zealand with autosomal dominant FSGS, revealed TRPC6 as the 
                    causative mutation [2]. This was subsequently confirmed in five other families 
                    with adult onset (age 17-57) autosomal dominant FSGS with variable progression 
                    to ESRD [3]. However, a recent study from Spain looking at a cohort of patients 
                    with FSGS, mainly sporadic, identified one child with a missense mutation who 
                    had not responded to steroids but had no other immunosuppression [4].
</p>
                <p>
                    1. Machuca et al. 2009. Human Mol Gen 18(2); 185-194.
                    <br />
                    2. Winn et al. 1999. Kidney International 55; 1241-1246.
                    <br />
                    3. Reiser et al 2005. Nature Genetics 37(7); 739 – 744.
                    <br />
                    4. Santin et al 2009. Nephrol Dial Transplant 24; 3089-3096.
                </p>
                
            </asp:Panel>
           
            <asp:CollapsiblePanelExtender ID="Panel4_CollapsiblePanelExtender" 
                runat="server" Enabled="True" TargetControlID="Panel4" ExpandedImage="~/images/collapse.jpg" CollapsedImage="~/images/expand.jpg" CollapsedSize="0"  ImageControlID="Image4" ExpandControlID="Image4" CollapseControlID="Image4" Collapsed="true">
            </asp:CollapsiblePanelExtender>
   <p style="font-weight: 700">
                    <asp:Image ID="Image5" runat="server" />
                    &nbsp;Developing biomarkers of disease</p>
            <asp:Panel ID="Panel5" runat="server" Width="90%">
            <p>Developing biomarkers of disease

</p>
                <p>
                    The third part of this project aims to authenticate three potential biomarkers 
                    of disease recurrence post transplantation that the research team in Bristol 
                    have developed. Ideally, a disease biomarker is sensitive and specific, but also 
                    easy to perform and reproducible giving a useful guide to disease activity. The 
                    work in Bristol has utilised the human conditionally immortalised podocyte cell 
                    line.
                </p>
                <ul>
                    <li>
                        <p>
                            &nbsp;Firstly, the slit diaphragm proteins relocate in response to exposure to 
                            ‘disease’ or relapse plasma. Nephrin, podocin and CD2AP demonstrate a 
                            cytoplasmic distribution in podocytes exposed to nephrotic plasma whereas after 
                            exposure to non-nephrotic plasma they demonstrate a cortical distribution [1].
                        </p>
                    </li>
                    <li>
                        <p>
                            Secondly, control of TRPC6 channels in the slit diaphragm appears to be 
                            important with the knowledge that mutations causing an increased function result 
                            in FSGS. Also, wild-type TRPC6 is seen to be induced in other glomerular 
                            diseases and TRPC6 activation is known to affect actin cytoskeleton 
                            rearrangement [2]. The research team have developed a marker of TRPC6 activation 
                            and will use this tool to investigate patient plasma in relapse and remission 
                            [3].
                        </p>
                    </li>
                    <li>
                        <p>
                            Lastly, utilising a phosphoprotein micro array, the team identified proteins 
                            that were phosphorylated in response to exposure to relapse plasma compared to 
                            remission plasma linking signalling pathways to remodelling of the actin 
                            cytoskeleton. The research team have identified that this is a consistent 
                            finding in all patients tested so far (unpublished work).
                        </p>
                    </li>
                </ul>
                <p>
                    1. Coward et al. 2005. J Am Soc Nephrol 16(3); 629-637.
                    <br />
                    2. Moller et al. 2009. J Am Soc Nephrol 18(1); 29-36.
                    <br />
                    3. Foster et al. 2009. Cell Calcium 45(4); 384-390.
                </p>
            </asp:Panel>
            <asp:CollapsiblePanelExtender ID="Panel5_CollapsiblePanelExtender" 
                runat="server" Enabled="True" TargetControlID="Panel5" ExpandedImage="~/images/collapse.jpg" CollapsedImage="~/images/expand.jpg" CollapsedSize="0"  ImageControlID="Image5" ExpandControlID="Image5" CollapseControlID="Image5" Collapsed="true">
            </asp:CollapsiblePanelExtender>
            <p style="font-weight: 700">
                <asp:Image ID="Image6" runat="server" /> &nbsp;Circulating factor theory
            </p>
            <asp:Panel ID="Panel6" runat="server" Width="90%">
            <p style="font-weight: 700">Circulating factor theory

</p>
                <p>
                    In 1974 Robert Shalhoub was the first to postulate on the theory of a 
                    circulating factor inducing nephrosis in NS. He postulated that abnormal T-cell 
                    function resulted in the secretion of a ‘circulating chemical mediator’, which 
                    was toxic to the Glomerular Basement Membrane (GBM) [1].
                </p>
                <p>
                    The circulating theory is reinforced clinically by: the high risk of disease 
                    recurrence post transplant; the reduction in proteinuria following plasma 
                    exchange in post transplant recurrence and the in-vitro finding that disease 
                    plasma induces nephrotic changes in cultured podocytes.
                </p>
                <p>
                    Virginia Savin and team from Wisconsin, in 1992 described an in-vitro bioassay 
                    to measure an increase in albumin permeability of isolated rat glomeruli which 
                    allowed for measurement of the change in permeability according to the 
                    environment that the glomeruli was exposed to [2]. They have since shown that 
                    plasma extracted from a series of patients in relapse of FSGS increased the 
                    permeability of the glomeruli compared to normal plasma [3]. This group has also 
                    worked on identifying the potential circulating factor describing a protein 
                    which 30-50kDa in size and heat and protease sensitive [3]. However it has 
                    proved difficult to isolate the factor and the permeability assay has also 
                    proved difficult to replicate in other laboratories. Also, the presence of the 
                    factor does not correlate with degree of proteinuria and presence pre 
                    transplantation did not correlate to recurrence risk post transplantation [4].
                </p>
                <p>
                    Because of these difficulties it is probable that there is some other mechanism 
                    involved. One other associated theory is that in disease plasma there may be a 
                    factor missing or inhibited.
                </p>
                <p>
                    In support of this, it has been shown that the increase in permeability that 
                    glomeruli demonstrate in vitro on exposure to disease plasma can be prevented by 
                    exposure to normal plasma just prior. Also, exposing cells to fetal calf serum 
                    compared to normal plasma also induced nephrotic changes [5]. Furthermore, one 
                    group demonstrated that plasma from children with Podocin mutations (and 
                    therefore not a circulating factor) also caused increased permeability in 
                    isolated glomeruli. Interestingly this effect was negated by addition of 
                    homologous urine (but not control urine). However, it is not possible to say if 
                    this loss of factor is a secondary or primary effect [6].
                </p>
                <p>
                    1. Shalhoub RJ 1974 Lancet. 2(7880):556-60.
                    <br />
                    2. Savin V et al 1992 J Am Soc Nephrol. 3(6):1260-9.
                    <br />
                    3. Sharma M et al 1999 J Am Soc Nephrol. 10(3);552-61.
                    <br />
                    4. Godfrin Y 1997
                    <br />
                    5. Coward 2005
                    <br />
                    6. Carraro 2002
                </p>
            </asp:Panel>
            <asp:CollapsiblePanelExtender ID="Panel6_CollapsiblePanelExtender" 
                runat="server" Enabled="True" TargetControlID="Panel6" ExpandedImage="~/images/collapse.jpg" CollapsedImage="~/images/expand.jpg" CollapsedSize="0"  ImageControlID="Image6" ExpandControlID="Image6" CollapseControlID="Image6" Collapsed="true">
            </asp:CollapsiblePanelExtender>
            <p style="font-weight: 700">
                <asp:Image ID="Image7" runat="server" /> &nbsp;Literature updates in SRNS/FSDGS
            </p>
            <asp:Panel ID="Panel7" runat="server" Width="90%">
            <p style="font-style: italic">Mekahli et al 2009. Ped Neph 24; 1525-1532.
                <br />
                Long-term outcome of idiopathic steroid-resistant nephrotic syndrome: a 
                multicentre study.
</p>
                <p>
                    Pierre Cochat’s group in Paris performed this retrospective study looking at 
                    outcome in SRNS. 78 patients in 8 centres were followed for between 1-20 years. 
                    They found patients were treated with a variety of strategies reflecting local 
                    preference and changes with time. During the follow-up period six patients 
                    developed deep vein thrombosis and 24 were admitted to hospital with an 
                    infection. Actuarial patient survival was 97% and renal survival at 5yrs, 10yrs 
                    and 15yrs was 75%, 58% and 53% respectively. The only clinical parameter on 
                    initial presentation which independently correlated with outcome was age with 
                    age below 10yrs at diagnosis beneficial (p&lt;0.001). 20 patients received 
                    transplants with 10 developing disease recurrence. 7 patients lost their graft 
                    in total with 4 due to disease recurrence.
                </p>
            </asp:Panel>
            <asp:CollapsiblePanelExtender ID="Panel7_CollapsiblePanelExtender" 
                runat="server" Enabled="True" TargetControlID="Panel7" ExpandedImage="~/images/collapse.jpg" CollapsedImage="~/images/expand.jpg" CollapsedSize="0"  ImageControlID="Image7" ExpandControlID="Image7" CollapseControlID="Image7" Collapsed="true">
            </asp:CollapsiblePanelExtender>
    <p style="font-weight: 700">
        <asp:Image ID="Image8" runat="server" />
&nbsp;What&#39;s New in podocytes</p>
            <asp:Panel ID="Panel8" runat="server" Width="90%">
            <p>This year Bristol University is hosting an international Podocyte conference 
                Bristol. More details are available at
                <a href="http://www.podocytebristol.org.uk.">www.podocytebristol.org.uk.</a></p>
            </asp:Panel>
            <asp:CollapsiblePanelExtender ID="Panel8_CollapsiblePanelExtender" 
                runat="server" Enabled="True" TargetControlID="Panel8" ExpandedImage="~/images/collapse.jpg" CollapsedImage="~/images/expand.jpg" CollapsedSize="0"  ImageControlID="Image8" ExpandControlID="Image8" CollapseControlID="Image8" Collapsed="true">
            </asp:CollapsiblePanelExtender>
            <p>
            </p>
            <p>
                &nbsp;</p>
    <p>
        Syndromes associated with SRNS</p>
    <p>
        League table of Units providing data</p>
    <p>
        &nbsp;</p></div>
</asp:Content>

