/***
 * Core application
 */

$('#MyTree').tree({ dataSource: dataSource })

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