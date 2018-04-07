var http = require('http');
var fs = require('fs');

function downloadFileList(fileList, path, index) {
	if(index >= fileList.length) return;

	download(fileList[index].url,fileList[index].name, path, function(err){
		if(err) throw err;
		downloadFileList(fileList, path, index+1);
	});
}

function download(fileUrl, fileName, path, callback) {
    var url = require('url'),
        http = require('http'),
        p = url.parse(fileUrl),
        timeout = 10000;
    
    var file = fs.createWriteStream(path+"/"+fileName);
    
    var timeout_wrapper = function( req ) {
        return function() {
            console.log('abort');
            req.abort();
            callback("File transfer timeout!");
        };
    };

    console.log("starting to download "+fileName+" ...");

    var request = http.get(fileUrl).on('response', function(res) {         
        var len = parseInt(res.headers['content-length'], 10);
        var downloaded = 0;

        var refreshIntervalId = setInterval(function(){
        	showProgress(fileName, downloaded, len);
        }, 200);
        
        res.on('data', function(chunk) {
            file.write(chunk);
            downloaded += chunk.length;
            
            // reset timeout
            clearTimeout( timeoutId );
            timeoutId = setTimeout( fn, timeout );
        }).on('end', function () {
            // clear timeout
            clearTimeout( timeoutId );
            clearInterval(refreshIntervalId);

            file.end();
            console.log(fileName + ' downloaded to: ' + path);
            callback(null);
        }).on('error', function (err) {
            // clear timeout
            clearTimeout( timeoutId );                
            callback(err);
        });           
    });
    
    
    // generate timeout handler
    var fn = timeout_wrapper( request );

    // set initial timeout
    var timeoutId = setTimeout( fn, timeout );
	
}

function humanFileSize(bytes, si) {
    var thresh = si ? 1000 : 1024;
    if(Math.abs(bytes) < thresh) {
        return bytes + ' B';
    }
    var units = si
        ? ['kB','MB','GB','TB','PB','EB','ZB','YB']
        : ['KiB','MiB','GiB','TiB','PiB','EiB','ZiB','YiB'];
    var u = -1;
    do {
        bytes /= thresh;
        ++u;
    } while(Math.abs(bytes) >= thresh && u < units.length - 1);
    return bytes.toFixed(1)+' '+units[u];
}


function showProgress(fileName, received, total){
    var percentage = (received * 100) / total;
    console.log(fileName+" : "+ (percentage).toFixed(2) + "% | " + humanFileSize(received,true) + " out of " + humanFileSize(total,true) + ".");
}

fs.readFile('./dependencies.json', 'utf8',function(err, data){
	if(err) throw err;
	var files = JSON.parse(data);
	
	downloadFileList(files.dependencies, "./jar_dependencies", 0);
});


