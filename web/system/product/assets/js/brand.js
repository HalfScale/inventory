$(function () {
	var brandModal = $('#brandModal'),
			brandAddBttn = $('#brandAddBttn'),
			brandModalSave = $('#brandModalSave'),
			brandModalClose = $('#brandModalClose');

	brandAddBttn.on('click', function () {
		brandModal.find('form').trigger('reset');
		brandModal.modal('show');
	});

	brandModalSave.on('click', function () {
		brandModal.find('form .dummy-submit').click();
	});

	brandModal.find('form').on('submit', function (e) {
		e.preventDefault();
		var fd = $(this).serializeForm();
		console.log('fd before submit', fd);
	});
});