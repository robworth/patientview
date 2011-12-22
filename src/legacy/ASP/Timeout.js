Type.registerNamespace("TSC.Timeout");


// Define the control class and properties.
TSC.Timeout.Timeout = function(element) 
{ 
    TSC.Timeout.Timeout.initializeBase(this, [element]);
    // class properties
    this._timeoutMinutes = null;
    this._aboutToTimeoutMinutes = null;
    this._timeoutURL = null;
    this._clientId = null;
    this._btnClientId = null;
    this._timerTimeout = null;
    this._timerAboutToTimeout = null;
    this._displayButton = null;

}


//// Create the prototype for the control.//
TSC.Timeout.Timeout.prototype = 
{ 
    initialize : function()  
    {   
        TSC.Timeout.Timeout.callBaseMethod(this, 'initialize');
        this._resetTimeout();
        
        this._onclickHandler = Function.createDelegate(this, this._onClick  );

        if (this._displayButton)        
            $addHandler($get(this._btnClientId), "click", this._onClick);
        else
            $addHandler($get(this._clientId), "click", this._onClick);
    },
    
    //Dispose Method
    dispose : function() 
    {
        $clearHandlers(this.get_element());        
        TSC.Timeout.Timeout.callBaseMethod(this, 'dispose');
    },
    
    get_timeoutMinutes : function() 
    {
      return this._timeoutMinutes;
    },
    
    set_timeoutMinutes : function(value) 
    {
        if (this._timeoutMinutes !== value) 
        {
            this._timeoutMinutes = value;
            this.raisePropertyChanged('timeoutMinutes');
        }
    },

    get_aboutToTimeoutMinutes : function() 
    {
      return this._aboutToTimeoutMinutes;
    },
    
    set_aboutToTimeoutMinutes : function(value) 
    {
        if (this._aboutToTimeoutMinutes !== value) 
        {
            this._aboutToTimeoutMinutes = value;
            this.raisePropertyChanged('aboutToTimeoutMinutes');
        }
    },


    get_timeoutURL : function() 
    {
      return this._timeoutURL;
    },
    
    set_timeoutURL : function(value) 
    {
        if (this._timeoutURL !== value) 
        {
            this._timeoutURL = value;
            this.raisePropertyChanged('timeoutURL');
        }
    },

    get_clientId : function() 
    {
      return this._clientId;
    },
    
    set_clientId : function(value) 
    {
        if (this._clientId !== value) 
        {
            this._clientId = value;
            this.raisePropertyChanged('clientId');
        }
    },
    
    get_btnClientId : function() 
    {
      return this._btnClientId;
    },
    
    set_btnClientId : function(value) 
    {
        if (this._btnClientId !== value) 
        {
            this._btnClientId = value;
            this.raisePropertyChanged('btnClientId');
        }
    },
        
    get_displayButton : function() 
    {
      return this._displayButton;
    },
    
    set_displayButton : function(value) 
    {
        if (this._displayButton !== value) 
        {
            this._displayButton = value;
            this.raisePropertyChanged('displayButton');
        }
    },
    
    _resetTimeout : function(e)
    {
        $get(this._clientId).style.display = 'none';
        clearTimeout(this._timerAboutToTimeout);
        clearTimeout(this._timerTimeout);

        this._showAboutToTimeoutDelegate = Function.createDelegate(this, this.showAboutToTimeout);
        this._timerAboutToTimeout = setTimeout(this._showAboutToTimeoutDelegate, this._aboutToTimeoutMinutes * 60 * 1000);
        this._timeoutDelegate = Function.createDelegate(this, this.timeout);
        this._timerTimeout = setTimeout(this._timeoutDelegate, this._timeoutMinutes * 60 * 1000);
    },
    
    showAboutToTimeout: function(e)
    {
        $get(this._clientId).style.display = 'block';
        ScrollToElement($get(this._clientId));
        window.focus();
    },
    
    timeout: function(e)
    {
        if (this._timeoutURL.length > 0)
        {
            window.location = this._timeoutURL;
        }
    },
    
    // Event delegates
    _onClick : function(e) 
    {
        CallServer();
        if (this.tagName == 'INPUT')
            this.parentNode.control._resetTimeout();
        else
            this.control._resetTimeout();
    }
}

TSC.Timeout.Timeout.registerClass('TSC.Timeout.Timeout', Sys.UI.Control); 

if (typeof(Sys) !== 'undefined') 
     Sys.Application.notifyScriptLoaded();


function ScrollToElement(theElement)
{

  var selectedPosX = 0;
  var selectedPosY = 0;
              
  while(theElement != null)
  {
    selectedPosX += theElement.offsetLeft;
    selectedPosY += theElement.offsetTop;
    theElement = theElement.offsetParent;
  }
                        		      
 window.scrollTo(selectedPosX,selectedPosY);

}


function ReceiveServerData(rValue) {}