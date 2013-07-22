/***
 * Core application
 */

$(document).ready(function () {
    $('label.tree-toggler').click(function () {
        $(this).parent().children('ul.tree').toggle(300);
    });
});


// Fuelux tree

var DataSource = function (options) {
    this._formatter = options.formatter;
    this._columns = options.columns;
    this._data = options.data;
};

DataSource.prototype = {

    columns: function () {
        return this._columns;
    },

    data: function (options, callback) {

        var self = this;
        if (options.search) {
            callback({ data: self._data, start: start, end: end, count: count, pages: pages, page: page });
        } else if (options.data) {
            callback({ data: options.data, start: 0, end: 0, count: 0, pages: 0, page: 0 });
        } else {
            callback({ data: self._data, start: 0, end: 0, count: 0, pages: 0, page: 0 });
        }
    }
};

var treeDataSource = new DataSource({
    data: [
        { name: 'Folder 1', type: 'folder', additionalParameters: { id: 'F1' },
            data: [
                { name: 'Sub Folder 1', type: 'folder', additionalParameters: { id: 'FF1' } },
                { name: 'Sub Folder 2', data: [
                    {name: 'sub sub folder 1', type: 'folder', additionalParameters: { id: 'FF21' }},
                    {name: 'sub sub item', type: 'item', additionalParameters: { id: 'FI2' }}
                ], type: 'folder', additionalParameters: { id: 'FF2' }},
                { name: 'Item 2 in Folder 1', type: 'item', additionalParameters: { id: 'FI2' } }
            ]
        },
        { name: 'Folder 2', type: 'folder', additionalParameters: { id: 'F2' } },
        { name: 'Item 1', type: 'item', additionalParameters: { id: 'I1' } },
        { name: 'Item 2', type: 'item', additionalParameters: { id: 'I2' } }
    ],
    delay: 400
});

$('#MyTree').tree({dataSource: treeDataSource});

$('#tree-selected-items').on('click', function () {
    console.log("selected items: ", $('#MyTree').tree('selectedItems'));
});

$('#MyTree').on('loaded', function (evt, data) {
    console.log('tree content loaded');
});

$('#MyTree').on('opened', function (evt, data) {
    console.log('sub-folder opened: ', data);
});

$('#MyTree').on('closed', function (evt, data) {
    console.log('sub-folder closed: ', data);
});

$('#MyTree').on('selected', function (evt, data) {
    console.log('item selected: ', data);
});


/*
console.log("SCCM Mock");

var json = {}

var jobs = asArray(json);

console.log(jobs);

var jobModel =
{
    day : ko.observable("today"),
    jobs : ko.observableArray([]),

    render : function(json)
    {
        console.log("JSON: " + JSON.stringify(json));
        if(json)
             jobModel.jobs(asArray(json));
    },

    refresh : function()
    {
        $.ajax("/user/list")
            .done(function(j)
            {
                console.log(JSON.stringify(j.data));

                if(j.status == 0)
                    jobModel.refresh(j.data)
                else
                    console.log("Fail : " + JSON.stringify(j))

            }).fail(function(jqxhr, textStatus, error)
            {
                console.log("Fail " + error.message);
            });
    }
};

ko.applyBindings(jobModel);

$().ready(function(){
    try
    {
        jobModel.render(json);
    }
    catch(e)
    {
        console.log("Exception handle : " + e.message)
    }
});

/* CRUD Example
//TODO Define view mode
// Here's my data model
var userModel =
{
    uuid : ko.observable(""),
    firstName : ko.observable(""),
    lastName : ko.observable(""),
    email : ko.observable(""),
    users : ko.observableArray([]),

    edit : function(uuid, first, last, email)
    {
        userModel.uuid(uuid);
        userModel.firstName(first);
        userModel.lastName(last);
        userModel.email(email);
    },

    save : function()
    {
        var user =
        {
            uuid: userModel.uuid(),
            firstName: userModel.firstName(),
            lastName: userModel.lastName(),
            email: userModel.email()
        }

        $.getJSON("/user/save",
            {user: JSON.stringify(user)},
            function(j)
            {
                console.log(JSON.stringify(j.data));
                if(j.status == 0)
                {
                    userModel.refresh();
                    userModel.clear()
                }
                else
                {
                    alert("Failed saving a user");
                }
            }
        )
    },

    clear : function()
    {
        userModel.edit("","","","")
    },

    update : function(json)
    {
        if(json)
         userModel.users(asArray(json));
    },

    select : function(user)
    {
        console.log("Select : " + user.uuid);
        userModel.edit(user.uuid, user.firstName, user.lastName, user.email)
    },

    erase : function(user)
    {
        console.log("Erase : " + user);
        $.getJSON("/user/remove", user, function(j)
            {
             console.log(JSON.stringify(j.data));
             if(j.status == 0)
                userModel.users.remove(user);
             else
                alert("Failed removing user");
            }
        )
    },

    // Ajax calls
    refresh : function()
    {
        $.ajax("/user/list")
            .done(function(j)
            {
                console.log(JSON.stringify(j.data));

                if(j.status == 0)
                    userModel.update(j.data)
                else
                    console.log("Fail : " + JSON.stringify(j))

            }).fail(function(jqxhr, textStatus, error)
            {
                console.log("Fail " + error.message);
            });
    }

   // this.fullName = ko.computed(function() {
   // Knockout tracks dependencies automatically. It knows that fullName depends on firstName and lastName, because these get called when evaluating fullName.
   //     return this.firstName() + " " + this.lastName();
   // }, this);
};

ko.applyBindings(userModel);

//Fill in user description
//userModel.edit("21212-221", "Victor", "Benarbia", "victor.benarbia@arm.com");

//var json = [{"firstName":"John00","lastName":"Doe","email":"johdoe00@arm.com"},{"firstName":"John01","lastName":"Doe","email":"johdoe01@arm.com"},{"firstName":"John02","lastName":"Doe","email":"johdoe02@arm.com"},{"firstName":"John03","lastName":"Doe","email":"johdoe03@arm.com"},{"firstName":"John04","lastName":"Doe","email":"johdoe04@arm.com"},{"firstName":"John05","lastName":"Doe","email":"johdoe05@arm.com"},{"firstName":"John06","lastName":"Doe","email":"johdoe06@arm.com"},{"firstName":"John07","lastName":"Doe","email":"johdoe07@arm.com"},{"firstName":"John08","lastName":"Doe","email":"johdoe08@arm.com"},{"firstName":"John09","lastName":"Doe","email":"johdoe09@arm.com"}]

//TODO Ajax call
$().ready(function(){
   try
   {
       userModel.refresh();
   }
   catch(e)
   {
     console.log("Exception handle : " + e.message)
   }
});
*/