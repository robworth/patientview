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
(function pageLoad()
 {
	 var menu,userAgent=navigator.userAgent.toLowerCase(),opera=/opera/.test(userAgent),onLoad=function()
	                    {
		                    var name="AJXCSSMenuCeGKRNA";
		                    var divs=document.getElementsByTagName("div");

		                    for(var i=0;i<divs.length;i++)
		                    {
			                    if(divs[i].className==name)
			                    {
				                    menu=divs[i];
				                    var lis=menu.getElementsByTagName("li");

				                    for(var n=lis[0];n;n=n.nextSibling)
					                    if(n.tagName=="LI")
					                    {
						                    n.ajxtop=true;

						                    if(opera)
							                    n.style.width=n.offsetWidth-(n.className.indexOf("tlast")==-1? 0:0);
					                    }

				                    for(var j=0;j<lis.length;j++)
				                    {
					                    lis[j].className=lis[j].className;
					                    lis[j].style.position="static";
					                    var uls=lis[j].getElementsByTagName("ul");

					                    if(uls.length>0)
						                    uls[0].style.display="none";

					                    lis[j].shown=lis[j].show=false;

					                    lis[j].onmouseover=function()
					                                       {
						                                       clearTimeout(menu.timer);

						                                       if(this.className.indexOf("ajxover")==-1)
							                                       this.className+=" ajxover";

						                                       this.show=true;

						                                       menu.timer=setTimeout(update,160);
					                                       }

					                                       ;
					                    lis[j].onmouseout=function()
					                                      {
						                                      clearTimeout(menu.timer);

						                                      if(!this.shown)
							                                      this.className=this.className.replace(new RegExp(" ?ajxover\\b"), "");

						                                      this.show=false;

						                                      menu.timer=setTimeout(update,600);
					                                      }

					                                      ;
				                    }
			                    }
		                    }
	                    }

	                    ,update=function()
	                            {
		                            var lis=menu.getElementsByTagName("li");

		                            for(var i=lis.length-1;i>=0;i--)
		                            {
			                            if(lis[i].show)
			                            {
				                            if(!lis[i].shown)
				                            {
					                            var uls=lis[i].getElementsByTagName("ul");

					                            if(uls.length>0)
					                            {
						                            lis[i].style.position="relative";
						                            uls[0].style.display="block";
						                            lis[i].shown=true;
					                            }
				                            }
			                            }
			                            else
			                            {
				                            var uls=lis[i].getElementsByTagName("ul");

				                            if(uls.length>0)
				                            {
					                            uls[0].style.display="none";
					                            lis[i].style.position="static";
					                            lis[i].shown=false;

					                            if(lis[i].className.indexOf("ajxover")!=-1)
						                            lis[i].className=lis[i].className.replace(new RegExp(" ?ajxover\\b"), "");
				                            }
			                            }
		                            }
	                            }

	                            ,addOnReady=function(f,fu)
	                                        {
		                                        var isReady=false,ready=function()
		                                                                {
			                                                                if(!isReady)
			                                                                {
				                                                                isReady=true;
				                                                                f();
			                                                                }

			                                                                ;
		                                                                }

		                                                                ;

		                                        if(document.addEventListener)
		                                        {
			                                        document.addEventListener('DOMContentLoaded',ready,false);
			                                        window.addEventListener("load",ready,false);
			                                        window.addEventListener("unload",fu,false);
		                                        }

		                                        if(window.attachEvent)
			                                        window.attachEvent("onload",ready);

		                                        if(document.documentElement.doScroll&&window==top)
		                                        {
			                                        (function()
			                                         {
				                                         if(!isReady)
				                                         {
					                                         try
					                                         {
						                                         document.documentElement.doScroll("left");
					                                         }
					                                         catch(E)
					                                         {
						                                         setTimeout(arguments.callee,0);
						                                         return;
					                                         }

					                                         ready();
				                                         }
			                                         }

			                                        )()
		                                        }
	                                        }

	                                        ;
	 addOnReady(onLoad, onLoad);
 }

)();
