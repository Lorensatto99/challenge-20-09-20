document.addEventListener('DOMContentLoaded',init);

function init(){
  const txtElement = document.querySelector('.txt-type');
  const words = JSON.parse(txtElement.getAttribute('data-words'));
  const wait = txtElement.getAttribute('data-wait');

  new TypeWriter(txtElement,words,wait);
}

const TypeWriter = function(txtElement,words,wait = 3000){
  this.txtElement = txtElement;
  this.words = words;
  this.txt = "";
  this.wordIndex = 0;
  this.wait = parseInt(wait,10);
  this.type();
  this.isDeleting = false;
}

TypeWriter.prototype.type = function(){
  
  const current = this.wordIndex % this.words.length;

  const fulltxt = this.words[current];
  const last = this.words[this.words.length-1];

  if(this.isDeleting){
    this.txt = fulltxt.substring(0,this.txt.length-1)
  }

  else{
    this.txt = fulltxt.substring(0,this.txt.length+1)
  }


  this.txtElement.innerHTML = `<span class="txt">${this.txt}</span>`;

  let typeSpeed = 100;

  if(this.isDeleting){
    typeSpeed /= 2; 
  }

  if(!this.isDeleting && this.txt === fulltxt){
    typeSpeed = this.wait;
    this.isDeleting = true;
  }

  else if(this.isDeleting && this.txt ===''){ 
    this.isDeleting = false;
    this.wordIndex++;
    typeSpeed = 200; //velocidade de digitação
  }  

  setTimeout(() => this.type(), typeSpeed); 
}

const mobileButton = document.querySelector('.mobile-btn');
mobileButton.onclick = function() {
  this.classList.toggle('active');
  document.querySelector('.mobile-menu').classList.toggle('active');
}

const menuItems = document.querySelectorAll('a[href^="#"]');
const voltarTopo = document.querySelector(".logo");

voltarTopo.addEventListener("click", function(){
  window.scroll({
    top:0,
    behavior:"smooth",
  })
})

var scrollBtn = $(".c-scroll-up")
var formOffset = $("form").offset().top
var formHeight = $("form").innerHeight()


$(window).scroll(function() {
    if ($(window).scrollTop() > formOffset - formHeight) {
      scrollBtn.addClass('is--active');
    } else {
      scrollBtn.removeClass('is--active');
    }
});
  
scrollBtn.on('click', function(e) {
  e.preventDefault();
  $('html, body').animate({scrollTop:0}, '500');
});


menuItems.forEach(item => {
  item.addEventListener('click',scrollToIdOnClick)
})

function scrollToIdOnClick(e){
  event.preventDefault();
  const element = e.target;
  const id = element.getAttribute('href');
  const to = document.querySelector(id).offsetTop;
  const menuHeaderTamanho = document.querySelector("header")
  window.scroll({
    top:to - 90,
    behavior:"smooth",
  });
}


const btnLeiaMais = document.querySelectorAll('.content-infos__text--button');

function leiamais() {
  this.previousElementSibling.classList.toggle("active");

  if(this.previousElementSibling.classList.contains("active")){
    this.innerText = "Leia menos"
  }

  else{
    this.innerText = "Leia mais"
  }
}

btnLeiaMais.forEach((i) => {
  i.addEventListener('click', leiamais);
});