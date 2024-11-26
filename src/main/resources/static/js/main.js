var mode_init = localStorage.getItem('mode');

if (mode_init == 'active') {
  $('.side-menu').addClass('active');
  $('#side_hamburger_expand').addClass('active');
}

$('#side_hamburger_expand').on('click', function () {
  var mode = localStorage.getItem('mode');

  if (mode == '' || mode == null) {
    $('.side-menu').addClass('active');
    $(this).addClass('active');
    localStorage.setItem('mode', 'active'); ;
  } else {
    $('.side-menu').removeClass('active');
    $(this).removeClass('active');
    localStorage.setItem('mode', '');
  }
})

$('body').on('click', function() {
  $('.collapse').collapse('hide');
})