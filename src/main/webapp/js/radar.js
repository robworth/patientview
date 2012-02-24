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
    init: function() {
        $(document).on("click", ".diagnosisRedirect", null, function() {
            jQuery('[id*="diagnosisLink"]').click();
        });
    }
}

var patientsListingPage = {
    init: function() {
        jQuery(".patientsLisitingTable").tablesorter({
            sortList: [[4,1]],
            // disable sorting on certain headers
            headers: {
                0: {
                    sorter: false
                },
                1: {

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

