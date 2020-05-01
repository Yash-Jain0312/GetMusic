<html>
    <head>
    </head>
    <body>
        <div style="text-align: center">
            <h1>${TITLE}</h1>
            <img src="${IMAGE}" width="200" style="display: block; position: relative; left: 400px; margin-bottom: 20px">
            <audio controls controlsList="nodownload">
                <source src="${SOURCE}"/>
            </audio>
            <form action="/music" method="GET">
                <h2>Download The Song</h2>
                <a href="${DOWNLOAD}">
                    <button type="button">
                        Download Song
                    </button>
                </a>
            </form>
        </div>
    </body>
</html>