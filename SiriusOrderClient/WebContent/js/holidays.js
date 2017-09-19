$(function () {
    // Initialize appendGrid
    $('#tblAppendGrid').appendGrid({
        caption: 'My CD Collections',
        initRows: 1,
        columns: [
            { name: 'FederalHoliday', display: 'Federal Holiday', type: 'text', ctrlAttr: { maxlength: 100 }, ctrlCss: { width: '160px'} },
            { name: 'Date', display: 'Date', type: 'date', ctrlAttr: { maxlength: 100 }, ctrlCss: { width: '130px'} },
            { name: 'DayofWeek', display: 'Day of Week', type: 'text', ctrlAttr: { maxlength: 10 }, ctrlCss: { width: '100px'} }
        ],
        //grab holidays from database ajax call
        //init the data
    });
});