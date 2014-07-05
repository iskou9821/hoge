$(function() {
	window.App = Ember.Application.create();
	
	var CTX_ROOT="Jersey-Test";
	
	App.ApplicationAdapter = DS.RESTAdapter.extend({
		namespace:CTX_ROOT + "/rest"
	});
});
