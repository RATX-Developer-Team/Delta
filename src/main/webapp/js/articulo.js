$(function() {
    console.clear()
    var utils_ = UTILS__

    let texto = document.getElementById('textoDelArticulo')
    let texto_ = texto.firstElementChild.innerHTML.replaceAll('"','').replaceAll('&lt;','<').replaceAll('&gt;','>')
    texto.innerHTML = texto_
});