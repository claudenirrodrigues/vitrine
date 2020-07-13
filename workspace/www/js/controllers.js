angular.module('app.controllers', [])
  
.controller('homeCtrl', ['$scope', '$stateParams', '$timeout',  // The following is the constructor function for this page's controller. See https://docs.angularjs.org/guide/controller
// You can include any angular dependencies as parameters for this function
// TIP: Access Route Parameters for your page via $stateParams.parameterName
function ($scope, $stateParams, $timeout) {

	$scope.$on('$ionicView.enter', function (viewInfo, state) {
		
		console.log('$ionicView.enter');
		//$timeout(window.open('http://www.modavitrine.com.br', 'random_string', 'location=no'), 7000);
		$timeout(window.open('http://vitrinejw.jelastic.websolute.net.br/vitrineDashboard', 'random_string', 'location=no'), 7000);
		
		/*
		if(cordova){
			alert('cordova it work');
		}
		
		if(cordova.InAppBrowser){
			alert('cordova.InAppBrowser it work');
			cordova.InAppBrowser.open('http://www.modavitrine.com.br', 'random_string', 'location=no');
			//cordova.InAppBrowser.open('http://vitrinejw.jelastic.websolute.net.br/vitrineDashboard', 'random_string', 'location=no');
		}
		*/
		
		//ini
		/*
		var options = {
		  location: 'no',
		  clearcache: 'yes',
		  toolbar: 'no'
		};
		
		var target = '_self';//cordova.InAppBrowser = 'random_string', $cordovaInAppBrowser = '_self', '_blank', '_system'

		document.addEventListener("deviceready", function () {
			$cordovaInAppBrowser.open('http://vitrinejw.jelastic.websolute.net.br/vitrineDashboard', target, options)
			.then(function(event) {
				alert('$cordovaInAppBrowser.open it work!');
			})
			.catch(function(event) {
				alert('$cordovaInAppBrowser.open not work!');
			});


			$cordovaInAppBrowser.close();

		}, false);

		$rootScope.$on('$cordovaInAppBrowser:loadstart', function(e, event){

		});

		$rootScope.$on('$cordovaInAppBrowser:loadstop', function(e, event){
			
			// insert CSS via code / file
			$cordovaInAppBrowser.insertCSS({
				code: 'body {background-color:#FEE6D3;}'
			});

			// insert Javascript via code / file
			//$cordovaInAppBrowser.executeScript({
			//	file: 'script.js'
			//});
		});

		$rootScope.$on('$cordovaInAppBrowser:loaderror', function(e, event){

		});

		$rootScope.$on('$cordovaInAppBrowser:exit', function(e, event){

		});
		*/
		//fim
	});
	
}])
 