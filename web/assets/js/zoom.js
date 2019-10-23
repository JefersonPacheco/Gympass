/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

    function toggle_div_fun(id) {

    var divelement = document.getElementById(id);

    if(divelement.style.display == 'none')
      divelement.style.display = 'block';
    else
      divelement.style.display = 'none';
    }
    
    function toggle_div_fun_2(id) {
       
        if(id == 'sectiontohide'){ // Se for o 1, esconde o 2      
            var divelement2 = document.getElementById('sectiontohide2');
            divelement2.style.display = 'none';

            var divelement = document.getElementById(id);

            if(divelement.style.display == 'none')
                divelement.style.display = 'block';
            else
                divelement.style.display = 'none';       

        }else{
            var divelement2 = document.getElementById('sectiontohide');
            divelement2.style.display = 'none';

            var divelement = document.getElementById(id);

            if(divelement.style.display == 'none')
                divelement.style.display = 'block';
            else
                divelement.style.display = 'none';         
        }
    }

    function div_hide() {

    var divelement = document.getElementById('sectiontohide');
    var divelement2 = document.getElementById('sectiontohide2');
    
      divelement.style.display = 'none';
      divelement2.style.display = 'none';
    }
    
    function limpar(id) {
    document.getElementById(id).checked = false;
    document.getElementById(id+'n').checked = false;
    }

