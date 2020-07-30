'use strict';
$(document).ready(function() {
	$(window).resize(function() {				
    	if (window.matchMedia('(max-width: 600px)').matches) {
    		if ($('#open_nav').length == 0) {    		
    		  $('.page-header').append('<div class="hamburger" id="open_nav"><i class="fas fa-bars"></i></div>');
    		  $('body').append($('.page-header').find('.main-nav')[0]);
    		}
    	} else {
    		$('#open_nav').remove();
    		$('.page-header').append($('body').find('.main-nav')[0]);
    	}
	});
});