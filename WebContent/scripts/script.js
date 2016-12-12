function getStudent(url, id) {
	$(document).ready(function() {
		$.getJSON(url+id, function(data) {
			console.log("this data is called", data);
			$("#student"+id).append("Instrument:" + data.student.music.instrument + "<br />");
			$("#student"+id).append("Song Writer:" + data.student.music.songWriter + "<br />");
			$("#student"+id).append("Genre:" + data.student.music.genre);
			$("#a"+id).attr("onclick", "");
		});
	});

	
}
