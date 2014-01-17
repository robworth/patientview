/*
 * PatientView
 *
 * Copyright (c) Worth Solutions Limited 2004-2013
 *
 * This file is part of PatientView.
 *
 * PatientView is free software: you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation, either version 3 of the License,
 * or (at your option) any later version.
 * PatientView is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even
 * the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with PatientView in a file
 * titled COPYING. If not, see <http://www.gnu.org/licenses/>.
 *
 * @package PatientView
 * @link http://www.patientview.org
 * @author PatientView <info@patientview.org>
 * @copyright Copyright (c) 2004-2013, Worth Solutions Limited
 * @license http://www.gnu.org/licenses/gpl-3.0.html The GNU General Public License V3.0
 */

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


