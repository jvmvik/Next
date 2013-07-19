//
// Model
//
function Job(json)
{
    var that = this;
    that.request_type = json.request_type;
    that.user = json.user;
    that.client_id = json.clientID;
    that.start_time = json.start_time;
    that.run_time = json.run_time;
    that.cpu_time = json.cpu_time;
    that.status = json.status;
    that.protocol_version = json.protocol_version;
    that.mercury_version = json.mercury_version;
}

/***
 * Convert json Array to JS Array of Article
 */
function asArray(json)
{
    var array = [];
    for (var i = 0; i < json.length; i++)
    {
        array[i] = new Job(json[i]);
    }
    return array;
}
