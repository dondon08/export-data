
$(document).ready(function() {
	$('.table-datatable').DataTable();

	updateLabel();
	formatHarga();
	var isModalInfo = document.getElementById("isModalInfo").textContent;
	if ('true' == isModalInfo) {
		var myModal = document.getElementById('modalInfo');
		var modal = new bootstrap.Modal(myModal);
		modal.show();
	}
});

function updateLabel() {
	var labelCells = document.getElementsByClassName('priceLabel');
	for (var i = 0; i < labelCells.length; i++) {
		var labelCell = labelCells[i];
		var labelValue = labelCell.textContent;
		var formattedLabel = formatRupiah(labelValue);
		labelCell.textContent = formattedLabel;
	}
}

function formatRupiah(label) {
	// Format angka dengan tanda titik sebagai pemisah ribuan
	var formattedLabel = label.replace(/\B(?=(\d{3})+(?!\d))/g, '.');
	// Tambahkan simbol 'Rp' di depan label
	formattedLabel = 'Rp ' + formattedLabel;
	return formattedLabel;
}

function formatHarga() {
	// Mendapatkan nilai harga dari input
	var input = document.getElementById("inputPrice");

	if (null == input) {
		return;
	}

	var harga = input.value.replace(/\D/g, "");

	// Menghapus tanda titik ribuan dari nilai harga yang dimasukkan
	var hargaTanpaRibuan = harga.replace(/\./g, "");

	// Mengubah nilai harga menjadi ribuan dengan menambahkan tanda titik setiap 3 digit
	var hargaRibuan = hargaTanpaRibuan.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");

	// Memasukkan kembali nilai harga yang sudah diubah menjadi ribuan ke dalam input
	input.value = hargaRibuan;
}