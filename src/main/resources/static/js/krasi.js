function GetVendorId() {
    let currentRow=$(this).closest('tr');
    let currentNo=currentRow.find('td:eq(0)').find('a').text();
    let No=document.getElementById('vendorNo');
    No.value=currentNo;
    HideLookup();
}
function CallLeaseCard(obj){
    window.location='/leasecard/'+getID(obj);
}
function CallLeaseDetailCard(obj){
    window.location='/leasedetailcard/'+getDetailID(obj);
}
function CallVendorCard(obj){
    window.location='/vendorcard/'+getID(obj);
}
function CallCountryCard(obj){
    window.location='/countrycard/'+getID(obj);
}
function CallVehicleCard(obj){
    window.location='/vehiclecard/'+getID(obj);
}
function getID(obj){
    let currentRow=$(obj).closest('tr');
    let currentID=currentRow.find('td:eq(1)').text();
    return currentID
}
function getDetailID(obj){
    let currentRow=$(obj).closest('tr');
    let currentID=currentRow.find('td:eq(1)').text();
    let currentDetail=currentRow.find('td:eq(2)').text();
    return currentID+'/'+currentDetail
}
function SearchVendor(inp,main){
   if (inp.length>=2){
       CreateLookupVendor(inp,main);
   }
}
function CreateLookupCountry(keySearch,main){
    let temp=document.getElementById('lookup');
    if (temp!=null){
        temp.remove();
    }

    let basicDiv=document.createElement('div');

    main.appendChild(basicDiv);

    basicDiv.setAttribute('id','lookup');
    basicDiv.classList.add('dropdown-content');

    let form=document.createElement('form');

    basicDiv.appendChild(form);

    form.classList.add('order');
    form.classList.add('fixedTable');
    form.method='post';

    let table=document.createElement('table');
    table.setAttribute('role','grid');
    table.classList.add('fixedTable');

    form.appendChild(table);

    let thead=document.createElement('thead');
    thead.classList.add('listDownHeader');
    table.appendChild(thead);

    let rowHead=document.createElement('tr');
    let NoCaption=document.createElement('th');
    NoCaption.textContent='Country no.';
    let NameCaption=document.createElement('th');
    NameCaption.textContent='Country name';
    rowHead.appendChild(NoCaption);
    rowHead.appendChild(NameCaption);

    thead.appendChild(rowHead);

    let tbody=document.createElement('tbody');
    tbody.setAttribute('role','rowgroup');
    tbody.classList.add('listDown');
    table.appendChild(tbody);

    var requestOptions={method:'GET',redirect:'follow'};

    let restLink='';
    if (keySearch===""){
        restLink='http://localhost:8080/countrysmalllist';
    } else {
        restLink ='http://localhost:8080/countrysmalllist?key='+keySearch;
    }
    fetch(restLink,requestOptions)
        .then(response=>response.json())
        .then(json=>json.forEach(item => {

            let row = document.createElement('tr');
            row.setAttribute('role', 'row');
            let No = document.createElement('td');
            No.setAttribute('role', 'gridcell');
            let a = document.createElement('a');
            a.href = '/countrycard/' + item.no;
            a.textContent = item.no;

            No.appendChild(a);
            row.appendChild(No);

            let Name = document.createElement('td');
            Name.setAttribute('role', 'gridcell');
            let span = document.createElement('span');
            span.textContent = item.name;
            span.ondblclick=GetCountryId;

            Name.appendChild(span);
            row.appendChild(Name);
            tbody.appendChild(row);
        }));
    let divbutton=document.createElement('div');
    divbutton.classList.add('listDownHeader');
    form.appendChild(divbutton);
    divbutton.classList.add('modal-footer');
    let anew=document.createElement('a');
    divbutton.appendChild(anew);
    anew.classList.add('btn');
    anew.classList.add('key');
    anew.href='/countrycard';
    anew.textContent='new';
    let alist=document.createElement('a');
    divbutton.appendChild(alist);
    alist.classList.add('btn');
    alist.classList.add('key');
    alist.href='/countrylist';
    alist.textContent='list';

    ShowLookup(event);
}
function ShowLookup(event) {
    let grid = document.getElementById('lookup');
    grid.style.zIndex = '1';
    grid.style.width = '400px';
    grid.style.height = '300px';
    grid.style.left = event.clientX + 'px';
    grid.style.top = event.clientY + 'px';
    grid.style.display="block";
}
function SearchCountry(inp,main){
    if (inp.length>=2){
        CreateLookupCountry(inp,main)
    }
}
function GetCountryId() {
    let currentRow=$(this).closest('tr');
    let currentNo=currentRow.find('td:eq(0)').find('a').text();
    let No=document.getElementById('country');
    No.value=currentNo;
    HideLookup();
}
function HideLookup(){
    let list=document.getElementById('lookup');
    if (list!=null) {
        list.style.display = "none";
        list.remove();
    }
}
function GetVehicleId(){
    const id=document.getElementById('source_lookup_id').value;
    let currentRow=$(this).closest('tr');
    let currentNo=currentRow.find('td:eq(0)').find('a').text();
    document.getElementById(id).value=currentNo;
    HideLookup();
}
function CreateLookupVendor(keySearch,main){
    let temp=document.getElementById('lookup');
    if (temp!=null){
        temp.remove();
    }

    let basicDiv=document.createElement('div');

    main.appendChild(basicDiv);

    basicDiv.setAttribute('id','lookup');
    basicDiv.classList.add('dropdown-content');

    let form=document.createElement('form');

    basicDiv.appendChild(form);

    form.classList.add('order');
    form.classList.add('fixedTable');
    form.method='post';

    let table=document.createElement('table');
    table.setAttribute('role','grid');
    table.classList.add('fixedTable');

    form.appendChild(table);

    let thead=document.createElement('thead');
    thead.classList.add('listDownHeader');
    table.appendChild(thead);

    let rowHead=document.createElement('tr');
    let NoCaption=document.createElement('th');
    NoCaption.textContent='Vendor no.';
    let NameCaption=document.createElement('th');
    NameCaption.textContent='Vendor name';
    let VatCaption=document.createElement('th');
    VatCaption.textContent='Vat registration no.';
    rowHead.appendChild(NoCaption);
    rowHead.appendChild(NameCaption);
    rowHead.appendChild(VatCaption);

    thead.appendChild(rowHead);

    let tbody=document.createElement('tbody');
    tbody.setAttribute('role','rowgroup');
    tbody.classList.add('listDown');
    table.appendChild(tbody);

    var requestOptions={method:'GET',redirect:'follow'};

    let restLink='';
    if (keySearch===""){
        restLink='http://localhost:8080/vendorsmalllist';
    } else {
        restLink ='http://localhost:8080/vendorsmalllist?key='+keySearch;
    }
    fetch(restLink,requestOptions)
        .then(response=>response.json())
        .then(json=>json.forEach(item => {

            let row = document.createElement('tr');
            row.setAttribute('role', 'row');
            let No = document.createElement('td');
            No.setAttribute('role', 'gridcell');
            let a = document.createElement('a');
            a.href = '/vendorcard/' + item.no;
            a.textContent = item.no;

            No.appendChild(a);
            row.appendChild(No);

            let Name = document.createElement('td');
            Name.setAttribute('role', 'gridcell');
            let span = document.createElement('span');
            span.textContent = item.name;
            span.ondblclick=GetVendorId;

            Name.appendChild(span);
            row.appendChild(Name);

            let Vat = document.createElement('td');
            Vat.setAttribute('role', 'gridcell');
            let spanVat = document.createElement('span');
            spanVat.textContent = item.vatRegistration;
            spanVat.ondblclick=GetVendorId;

            Vat.appendChild(spanVat);
            row.appendChild(Vat);

            tbody.appendChild(row);
        }));
    let divbutton=document.createElement('div');
    divbutton.classList.add('listDownHeader');
    form.appendChild(divbutton);
    divbutton.classList.add('modal-footer');
    let anew=document.createElement('a');
    divbutton.appendChild(anew);
    anew.classList.add('btn');
    anew.classList.add('key');
    anew.href='/vendorcard';
    anew.textContent='new';
    let alist=document.createElement('a');
    divbutton.appendChild(alist);
    alist.classList.add('btn');
    alist.classList.add('key');
    alist.href='/vendorlist';
    alist.textContent='list';

    ShowLookup(event);
}
function CreateLookupVehicle(idElement,main){
    document.getElementById('source_lookup_id').value=idElement;

    let keySearch=document.getElementById(idElement).value;

    let temp=document.getElementById('lookup');
    if (temp!=null){
        temp.remove();
    }

    let basicDiv=document.createElement('div');

    main.appendChild(basicDiv);

    basicDiv.setAttribute('id','lookup');
    basicDiv.classList.add('dropdown-content');

    let form=document.createElement('form');

    basicDiv.appendChild(form);

    form.classList.add('order');
    form.classList.add('fixedTable');
    form.method='post';

    let table=document.createElement('table');
    table.setAttribute('role','grid');
    table.classList.add('fixedTable');

    form.appendChild(table);

    let thead=document.createElement('thead');
    thead.classList.add('listDownHeader');
    table.appendChild(thead);

    let rowHead=document.createElement('tr');
    let NoCaption=document.createElement('th');
    NoCaption.textContent='Vehicle no.';
    let NameCaption=document.createElement('th');
    NameCaption.textContent='Vehicle plate no.';
    let ModelCaption=document.createElement('th');
    ModelCaption.textContent='Model';
    rowHead.appendChild(NoCaption);
    rowHead.appendChild(NameCaption);
    rowHead.appendChild(ModelCaption);

    thead.appendChild(rowHead);

    let tbody=document.createElement('tbody');
    tbody.setAttribute('role','rowgroup');
    tbody.classList.add('listDown');
    table.appendChild(tbody);

    var requestOptions={method:'GET',redirect:'follow'};

    let restLink='';
    if (keySearch===""){
        restLink='http://localhost:8080/vehiclesmalllist';
    } else {
        restLink ='http://localhost:8080/vehiclesmalllist?key='+keySearch;
    }
    fetch(restLink,requestOptions)
        .then(response=>response.json())
        .then(json=>json.forEach(item => {

            let row = document.createElement('tr');
            row.setAttribute('role', 'row');
            let No = document.createElement('td');
            No.setAttribute('role', 'gridcell');
            let a = document.createElement('a');
            a.href = '/vehiclecard/' + item.no;
            a.textContent = item.no;

            No.appendChild(a);
            row.appendChild(No);

            let Name = document.createElement('td');
            Name.setAttribute('role', 'gridcell');
            let span = document.createElement('span');
            span.textContent = item.numberPlate;
            span.ondblclick=GetVehicleId;

            Name.appendChild(span);
            row.appendChild(Name);

            let Model = document.createElement('td');
            Model.setAttribute('role', 'gridcell');
            let spanModel = document.createElement('span');
            spanModel.textContent = item.vehicleModel;
            spanModel.ondblclick=GetVehicleId;

            Model.appendChild(spanModel);
            row.appendChild(Model);

            tbody.appendChild(row);
        }));
    let divbutton=document.createElement('div');
    divbutton.classList.add('listDownHeader');
    form.appendChild(divbutton);
    divbutton.classList.add('modal-footer');
    let anew=document.createElement('a');
    divbutton.appendChild(anew);
    anew.classList.add('btn');
    anew.classList.add('key');
    anew.href='/vehiclecard';
    anew.textContent='new';
    let alist=document.createElement('a');
    divbutton.appendChild(alist);
    alist.classList.add('btn');
    alist.classList.add('key');
    alist.href='/vehiclelist';
    alist.textContent='list';

    ShowLookup(event);
}
function SearchVehicle(idElement,main){
    if (document.getElementById(idElement).value.length>=2){
        CreateLookupVehicle(idElement,main);
    }
}


