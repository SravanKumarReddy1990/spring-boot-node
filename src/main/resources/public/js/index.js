$(function () {
  $('body p').append(" and Javascript!");
var Addition=require('./Addition.js');
console.log(Addition.AddNumber(1,2));

var localTutor=require('./NodeTutorial.js');
localTutor.NodeTutorial();
//localTutor.NodeTutorial.pTutor();
 $('body p').append(localTutor.NodeTutorial());
var tut = new localTutor.NodeTutorial();  
tut.pTutor();

 $('body p').append(tut.pTutor());

});
