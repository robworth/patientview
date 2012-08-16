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
    graphContainer: $('#graphContainer'),
    graphForm:      $('#graphForm'),
    graphEl:        $('#graph'),
    graph:          null,
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
    graphData:      null,
    graphDates:     null,
    fromDate:       null,
    toDate:         null,
    graphType:      null,

    init: function() {
        var that = this;

        that.fromDate = this.graphForm.find('#fromDate');
        that.toDate = this.graphForm.find('#toDate');
        that.graphType = this.graphForm.find('#graphType');

        this.graphForm.submit(function() {
            that.dataRequest();
            return false;
        });

        this.drawGraph();
    },

    drawGraph: function() {
        this.graphEl.html('');

        if (this.graphData && this.graphDates) {
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
                fromDate: that.fromDate.val(),
                toDate: that.toDate.val(),
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