$(function () {
	$('#userProfileForm').on('submit', function (e) {
		e.preventDefault();
		var formData = new FormData(this);

		for (var pair of formData.entries()) {
			console.log(pair[0] + ', ' + pair[1]);
		}

		$.ajax({
			url: $g.root_path + 'avatar',
			data: formData,
			type: 'POST',
			contentType: false, // NEEDED, DON'T OMIT THIS (requires jQuery 1.6+)
			processData: false, // NEEDED, DON'T OMIT THIS
			success: function (result) {
				sysAlert({
					text: result.response
				});
				
				imageInput.next('.custom-file-label').text('Choose a file');
			}
		});


	});

	var imageInput = $('#profileImageInput').on('change', function (e) {
		var selectedFile = e.currentTarget.files[0];
		var reader = new FileReader();
		var avatar = $('#user-avatar');
		var avatarSecondary = $('.user-avatar');
		
		var fileSize = ((selectedFile.size / 1024) / 1024).toFixed(4);

		if (fileSize > 5) {
			$(this).val('');
			sysConfirm({
				title: 'Invalid file size',
				text: 'File size is too large!',
				hideConfirmBtn: true
			});
		} else {
			avatar.attr('title', selectedFile.name);
			$(this).next('.custom-file-label').text(selectedFile.name);
			
			reader.onload = function (e) {
				avatar.attr('src', e.target.result);
				avatarSecondary.attr('src', e.target.result);
			};
			
			reader.readAsDataURL(selectedFile);
		}
	});
});