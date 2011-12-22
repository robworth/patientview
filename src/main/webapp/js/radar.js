// document ready
jQuery(function() {
   srnsPage.init();
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
            if(image.indexOf(self.expandImage) != -1) {
               image = image.replace(self.expandImage, self.collapseImage);
            } else {
              image = image.replace(self.collapseImage, self.expandImage);
            }

            jQuery(this).attr("src", image);

            jQuery(this).parents(self.infoItemSelector).find(self.infoSelector).toggleClass(self.hiddenClass);


        })
    }

}