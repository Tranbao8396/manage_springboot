$('#side_hamburger_expand').on('click', function () {
  $('.side-menu').toggleClass('active');
  $(this).toggleClass('active');
})

$('body').on('click', function() {
  $('.collapse').collapse('hide');
})