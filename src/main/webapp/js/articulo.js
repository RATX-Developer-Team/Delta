$(function() {
    console.clear()
    const params = new Proxy(new URLSearchParams(window.location.search), {
        get: (searchParams, prop) => searchParams.get(prop),
    });
    
    $('.secreto').hide()
    $('.secreto1').hide()

    if (params.codigo!=null && params.codigo!=0 && params.codigo!=-1) {
        $('.secreto').val(params.codigo)
        $('.secreto1').click()
    }
});