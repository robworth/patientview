// document ready
jQuery(function() {
    srnsPage.init();
    recruitmentPage.init();
    editPatientPage.init();
    patientsListingPage.init();
})


var srnsPage = {

    init:function() {
        this.infoItemSelector = ".infoItem";
        this.infoHandelSelector = ".infoHandle";
        this.infoSelector = ".info";
        this.hiddenClass = "infoItemHidden";
        this.registerListeners();
        this.expandImage = "expand";
        this.collapseImage = "collapse";
    },

    registerListeners:function() {
        var self = this;
        jQuery(this.infoHandelSelector).click(function() {
            var image = jQuery(this).attr("src");
            if (image.indexOf(self.expandImage) != -1) {
                image = image.replace(self.expandImage, self.collapseImage);
            } else {
                image = image.replace(self.collapseImage, self.expandImage);
            }

            jQuery(this).attr("src", image);
            jQuery(this).parents(self.infoItemSelector).find(self.infoSelector).toggleClass(self.hiddenClass);
        })
    }

}

var recruitmentPage = {
    init: function() {
        jQuery(".recruitmentTable").tablesorter({
            sortList: [
                [0,0]
            ],
            dateFormat: "uk"
        });
    }
}

var editPatientPage = {
    formIsDirty:false,
    init: function() {
        $(document).on("click", ".diagnosisRedirect", null, function() {
            jQuery('[id*="diagnosisLink"]').click();
        });
    }
}

var patientsListingPage = {
    init: function() {
        jQuery(".patientsLisitingTable").tablesorter({
            sortList: [
                [4,1]
            ],
            // disable sorting on certain headers
            headers: {
                0: {
                    sorter: false
                },
                2:{
                    sorter: false
                },
                3:{
                    sorter: false
                },
                6:{
                    sorter: false
                },
                7:{
                    sorter: false
                }
            },
            cancelSelection: false,
            dateFormat: "uk"
        });
    }
}

var radarUtility = {
    // sanitise date input
    sanitiseDateInput: function(input) {
        var input = jQuery(input);
        input.val(input.val().replace(".", "-").replace("/", "-"));
    },

    // called onblur - if blank replace with default value
    checkDetailsInput: function(element, defaultValue) {
        var element = jQuery(element);
        if (jQuery.trim(element.val()) == "") {
            element.val(defaultValue);
            element.addClass("grey");
        }
    },

    // called on click - if input has default value help text then remove on click
    removeDefaultValue: function(element, defaultValue) {
        var element = jQuery(element);
        if (element.val() == defaultValue) {
            element.val("");
            element.removeClass("grey");
        }
    }
}


