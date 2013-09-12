PatientView-API
===========

PatientView-API allows third party apps and sites with proper authentication, to use PV data for their users.

Data is supplied by PatientView server via json.,

API Specification
===============

###1.Test Results###
#####URL#####
- GET /data/testresult.json?{params}

#####Params#####
- unitcode, string, required
- page, integer, optional, default 1
- pageSize, integer, optional, default 20, max 100
- nhsno, String, optional
- testcode, String, optional

#####Returns#####
- PagedResultsWrapper<TestResult>
#####Example#####
<code>{
"results": [
                {
                "id": 543910896,
                "nhsno": "7531599198",
                "unitcode": "PATIENT",
                "datestamped": 1367462088000,
                "prepost": "",
                "testcode": "weight",
                "value": "55",
                "timestamp": 1367462088000,
                "sortingDatestamp": "2013-05-0210:34",
                "isoDatestamp": "2013-05-02T10:34:48",
                "isoDayDatestamp": "2013-05-02",
                "formattedDatestamp": "02/05/13 10:34"
                }
            ],
"pageSize": 20,
"pageNumber": 1,
"totalResults": 1
}
</code>
###2. Letters###
#####URL#####
- GET /data/letters.json?{params}

#####Params#####
- nhsno, String, required

#####Returns#####
- PagedResultsWrapper<Letter>
#####Example#####
<code>{
    "results": [
        {
            "id": 40254236,
            "nhsno": "9876543210",
            "unitcode": "RM301",
            "date": 1326816000000,
            "type": "Clinic Letter",
            "content": "Another letter",
            "formattedContent": "Another letter",
            "formattedDate": "18/01/12"
        }
    ],
    "pageSize": 20,
    "pageNumber": 1,
    "totalResults": 2
}</code>

###3.Medicines###
#####URL#####
- GET /data/medicines.json?{params}

#####Params#####
- nhsno, String, required

#####Returns#####
- PagedResultsWrapper<MedicineWithShortName>
#####Example#####
<code>{
    "results": [
        {
            "id": 98560704,
            "nhsno": "123456789",
            "unitcode": "RENALA",
            "startdate": 1378137600000,
            "name": "testname",
            "dose": "testdose",
            "shortname": "Renal A",
            "formattedStartDate": "03/09/13"
        }
    ],
    "pageSize": 20,
    "pageNumber": 1,
    "totalResults": 1
}</code>


###4. Authorisation###
All requests need to have the following: - ROLE_API_USER