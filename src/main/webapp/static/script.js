/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//sidebar
$(".button-collapse").sideNav();

//paralox over footer
$(document).ready(function () {
    $('.parallax').parallax();
});

// tabs til show order
$(document).ready(function () {
    $('ul.tabs').tabs();
});


// used for popup inspecters
$(document).ready(function () {
    // the "href" attribute of the modal trigger must specify the modal ID that wants to be triggered
    $('.modal').modal();
});


//??? kan ikke huske hvofor
$("select[required]").css({display: "block", height: 0, padding: 0, width: 0, position: 'absolute'});

// used for select input
$(document).ready(function () {
    $('select').material_select();
});



// to make images bigger on click
$(document).ready(function () {
    $('.materialboxed').materialbox();
});

