$(function(){
	window.App = Ember.Application.create();
	App.ApplicationAdapter = DS.FixtureAdapter.extend();
	
	App.TestItem = DS.Model.extend({
		name : DS.attr('string'),
		caption : DS.attr('string'),
		text : DS.attr('string'),
		//リレーションはなんかうまく動かないっぽいので使わないことにする。。。
		//testSubItems : DS.hasMany('testSubItem')
	});
	App.TestItem.FIXTURES = [
		{	
			id : 1,
			name : 'test1',
			caption : 'テストいち',
			text : 'テストです。１',
			testSubItems: [1, 2, 3]
		},
		{	
			id : 2,
			name : 'test2',
			caption : 'テストに',
			text : 'テストです。２',
			subItems: [4, 5, 6] 
		},
		{	
			id : 3,
			name : 'test3',
			caption : 'テストさん',
			text : 'テストです。３'
		}						
	];
	
	App.TestSubItem = DS.Model.extend({
		caption : DS.attr('string'),
		imgUrl : DS.attr('string') 
	});
	App.TestSubItem.FIXTURES = [
		{ id : 1,
		  caption : 'test1',
		  imgUrl : 'test1.jpg'},
		{ id : 2,
		  caption : 'test2',
		  imgUrl : 'test2.jpg'},
		{ id : 3,
		  caption : 'test3',
		  imgUrl : 'test3.jpg'}
	];
});