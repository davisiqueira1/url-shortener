async function shorten() {
    const longUrl = document.getElementById('longUrl').value;
    const result = document.getElementById('result');

    try {
        const response = await fetch('http://localhost:8080/api/shorten', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ longUrl })
        });

        if (!response.ok) {
            throw new Error('Could not shorten the URL. Please try again later.');
        }

        const data = await response.json();
        const baseUrl = "http://localhost:8080/";
        result.innerHTML = `Your Shortly link: <a href="${baseUrl + data.shortCode}" target="_blank">${baseUrl + data.shortCode}</a>`;
    } catch (error) {
        result.innerText = error.message;
    }
}
