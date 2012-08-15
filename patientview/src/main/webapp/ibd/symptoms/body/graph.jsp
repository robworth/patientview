<div class="span7" id="graphContainer">
    <form action="#" class="form-inline" id="graphForm">
        <input type="hidden" name="graphType" id="graphType" value="1" />

        <label class="control-label" for="fromDate">From:</label>
        <div class="input-append date datePicker">
            <input name="fromDate" id="fromDate" class="span2" size="16" type="text" value="" readonly>
            <span class="add-on"><i class="icon-th"></i></span>
        </div>

        <label class="control-label" for="toDate">To:</label>
        <div class="input-append date datePicker">
            <input name="toDate" id="toDate" class="span2" size="16" type="text" value="" readonly>
            <span class="add-on"><i class="icon-th"></i></span>
        </div>

        <input type="submit" class="btn btn-primary" value="Update" />
    </form>

    <div id="graph" class="linegraph"></div>
</div>