$(document).ready(function() {
    $('section').hide();
    $('section#head').show();
    
    $('a#a1').click(function() {
        $('section').hide();
        $('section#login').show();
    });
});

$('a.menu').click(function() {
    $this = $(this);
    $capa = $this.attr("href");
    $('section').hide();
    $('section' + $capa).show();
    if ($capa === "#registrarse") {
        $('a#enlaceterminos').click(function() {
            $('section').hide();
            $('section#terms').show();
        })
    }
    if($capa === "#terms"){
        $('a#returnlink').click(function() {
            $('section').hide();
            $('section#head').show();
        })
    }
});


//Para declarar funciones jQuery simples, haremos lo siguiente ...
//
//$.metodo = function(parametros){ //instrucciones }
//
//y luego para llamarla haremos lo siguiente ...
//
//$.metodo(parametros);

////// global. currently active menu item 
//var current_item = 0;
//
//// few settings
//var section_hide_time = 250;
//var section_show_time = 250;
//
//// jQuery stuff
//jQuery(document).ready(function($) {
//
//	// Switch section
//	$("a", '.mainmenu').click(function() 
//	{
//		if( ! $(this).hasClass('active') ) { 
//			current_item = this;
//			// close all visible divs with the class of .section
//			$('.section:visible').fadeOut( section_hide_time, function() { 
//				$('a', '.mainmenu').removeClass( 'active' );  
//				$(current_item).addClass( 'active' );
//				var new_section = $( $(current_item).attr('href') );
//				new_section.fadeIn( section_show_time );
//			} );
//		}
//		return false;
//	});		
//});