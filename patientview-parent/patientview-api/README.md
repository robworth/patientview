PatientView-API
===========

PatientView-API allows third party apps and sites with proper authentication, to use PV data for their users.

Data is supplied by PatientView server via json.,

API Specification
===============

1.Test Results
URL
- GET /data/testresult.json?{params}

Params
- unitcode, string, required
- page, integer, optional, default 1
- pageSize, integer, optional, default 20, max 100
- nhsno, String, optional
- testcode, String, optional

Returns
- PagedResultsWrapper<TestResult>
Example
{
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

2. Letters
URL
- GET /data/letters.json?{params}

Params
- username, String, required

Returns
- List<Letter>
Example
[
    {
    "id": 40254192,
    "nhsno": "6810341560",
    "unitcode": "RENALA",
    "date": 1370534400000,
    "type": "<script>alert('FAIL');</script>",
    "content": "<script>alert('FAIL');</script>\n5\n6",
    "formattedContent": "&lt;script&gt;alert&#x28;&#x27;FAIL&#x27;&#x29;&#x3b;&lt;&#x2f;script&gt;<br/>5<br/>6",
    "formattedDate": "07/06/13"
    }
]

3.Medicines
URL
- GET /data/medicines.json?{params}

Params
- nhsno, String, required

Returns
- List<MedicineWithShortName>
Example
[
    {
    "id": 98560683,
    "nhsno": "1710872306",
    "unitcode": "SNC01",
    "startdate": 1332864010000,
    "name": "Hydroxychloroquine",
    "dose": "400 mg",
    "shortname": "UNKNOWN UNIT:SNC01",
    "formattedStartDate": "28/03/12"
    }
]

4.Sign off
URL
- GET /data/signoff.json

Return
- String
Example
success

5. Authorisation
All requests need to have the following: - ROLE_API_USER