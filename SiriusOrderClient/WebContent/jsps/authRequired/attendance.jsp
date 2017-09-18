<!DOCTYPE html>
<html>

<head>
  <meta charset="utf-8">
  <title>Search for attendance records</title>
  <link rel="stylesheet" type="text/css" href="attendance.css">
</head>

<body>

  <div id="attendanceSearchForm" class="searchForm">
    <form class="attendanceForm">
      <input type="text" name="name" value="" />
      <input type="text" name="email" />
      <select name="location">
        <option value="%"> location</option>
        <option value="Austin, Texas"> Austin, Texas</option>
        <option value="Housten, Texas">Housten, Texas</option>
        <option value="Omaha, Florida">Omaha, Florida</option>
      </select>
      <select name="view">
        <option value="view">view</option>
        <option value="display">Display</option>
        <option value="PDF">PDF</option>
      </select>
		
      <input type="date" name="startDate" />
      <input type="date" name="endDate" />

      <select name="range">
          <option value="thisWeek">This Week</option>
          <option value="lastWeek">Last Week</option>
          <option value="lastTenDays">Last Ten Days</option>
          <option value="thisMonth"> This Month</option>
          <option value="lastMonth">Last Month</option>
      </select>
    </form>
    <input form="attendanceSarchForm" type="submit" value="Generate Report" />
  </div>
</body>

</html>