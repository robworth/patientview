var IBD = {};

IBD.AddMedicineInit = function() {
    var medicationType              = $('#medicationTypeId'),
        medicationContainer         = $('#medicationContainer'),
        medicationListsContainer    = $('#medicationLists'),
        medicationLists             = medicationListsContainer.find('.medicationList'),
        medicationDoseContainer     = $('#medicationDoseContainer'),
        medicationDoseLists         = medicationDoseContainer.find('.medicationDoseList'),
        medicationOtherContainer    = $('#medicationOtherContainer'),
        otherMedicationInput        = $('#otherMedication'),
        disableLists                = function(list) {
            if (list) {
                list.each(function(i, el) {
                    $(el).addClass('hidden')[0].disabled = true;
                });
            }
        },
        enableList                  = function(list) {
            if (list) {
                list.removeClass('hidden')[0].disabled = false;
            }
        };

    medicationType.on('change', function() {
        var medicationTypeId = parseInt(medicationType.find('option:selected').val());

        // if a value is selected then show the corresponding list of medications else hide if they are showing
        if (medicationTypeId !== NaN && medicationTypeId > 0) {
            medicationContainer.removeClass('hidden');
            disableLists(medicationLists);
            enableList($('#medicationType' + medicationTypeId + '-medications'));
        } else {
            medicationContainer.addClass('hidden');
            disableLists(medicationLists)
        }
    });

    medicationLists.on('change', function(el) {
        var medicationId = parseInt($(this).find('option:selected').val());

        // if a value is selected then show the corresponding list of dosages else hide if they are showing
        // if the value is -2 then this is the 'other' option been selected in which case show the other text field
        if (medicationId === NaN || medicationId <= 0) {
            medicationDoseContainer.addClass('hidden');
            disableLists(medicationDoseLists);

            if (medicationId !== NaN && medicationId === -2) {
                medicationOtherContainer.removeClass('hidden');
            }
        } else {
            otherMedicationInput.val('');
            medicationOtherContainer.addClass('hidden');
            medicationDoseContainer.removeClass('hidden');
            disableLists(medicationDoseLists);
            enableList($('#medication' + medicationId + '-dosages'));
        }
    })
};

IBD.Symptoms = {
    graphContainer:         $('#graphContainer'),
    graphForm:              $('#graphForm'),
    symptomsForm:           $('#symptomsForm'),
    graphEl:                $('#graph'),
    graph:                  null,
    graphOptions:   {
        start_value: 0,
        //label_count: 10,
        label_step: 2,
        markers: 'circle',
        colours: {
            scores: '#0066CC'
        },
        grid: true,
        background_colour: 'transparent'
    },
    graphData:              null,
    graphDates:             null,
    graphFormFromDate:      null,
    graphFormToDate:        null,
    symptomsFormFromDate:   null,
    symptomsFormToDate:     null,
    graphType:              null,

    init: function() {
        var that = this;

        // get elements from the graph form
        that.graphFormFromDate = this.graphForm.find('#fromDate');
        that.graphFormToDate = this.graphForm.find('#toDate');
        that.graphType = this.graphForm.find('#graphType');

        // find elements in the symptoms form
        that.symptomsFormFromDate = this.symptomsForm.find('#fromDate');
        that.symptomsFormToDate = this.symptomsForm.find('#toDate');

        // add submit on the graph form so it makes an ajax request for the data
        this.graphForm.submit(function() {
            that.dataRequest();
            return false;
        });

        // on the symptoms form we want to copy the from and two date from the graph form if they have been set before
        this.symptomsForm.submit(function() {
            that.symptomsFormFromDate.val(that.graphFormFromDate.val());
            that.symptomsFormToDate.val(that.graphFormToDate.val());
        });

        this.drawGraph();
    },

    drawGraph: function() {
        this.graphEl.html('');

        if (this.graphData && this.graphData.length > 0 && this.graphDates && this.graphDates.length > 0) {
            this.graphOptions.labels = this.graphDates;

            this.graph = new Ico.LineGraph(
                this.graphEl[0],
                {
                    scores: this.graphData
                },
                this.graphOptions
            );
        }
    },

    dataRequest: function() {
        var that = this;

        $.ajax({
            url: '/ibd/graph-data.do',
            dataType: 'json',
            data: {
                fromDate: that.graphFormFromDate.val(),
                toDate: that.graphFormToDate.val(),
                graphType: that.graphType.val()
            },
            success: function(data) {
                that.graphData = data.scores;
                that.graphDates = data.dates;
                that.drawGraph();
            },
            error: function(jqXHR) {
                this.graphEl.html('Could not load data: ' + jqXHR.responseText);
            }
        })
    },

    testData: function(min, max, method) {
        var a = [], i;
        for (i = min; i < max; i++) {
            a.push(method.apply(this, [i]));
        }
        return a;
    }
};

$(function() {
    $('.datePicker').datepicker({
        format:'dd-mm-yyyy'
    }).on('changeDate', function (ev) {
            $(ev.currentTarget).datepicker('hide')
        });

    // if its the add medicine page then set up medication code
    if ($('#addMedication').length > 0) {
        IBD.AddMedicineInit();
    }

    // if symptoms container is present then set up symptoms code
    if ($('#symptomsContainer').length > 0) {
        IBD.Symptoms.init();
    }
});