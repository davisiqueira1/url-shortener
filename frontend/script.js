async function shorten() {
    const longUrl = document.getElementById('longUrl').value;
    const result = document.getElementById('result');

    try {
        const validUrl = validateUrl(longUrl);

        if (!validUrl) {
            throw new Error('Please enter a valid URL.');
        }

        const baseUrl = 'https://api.davisiqueira.com'

        const response = await fetch(`${baseUrl}/api/shorten`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({ longUrl: validUrl })
        });

        if (!response.ok) {
            throw new Error('Could not shorten the URL. Please try again later.');
        }

        const data = await response.json();
        const shortenUrl = `${baseUrl}/${data.shortCode}`;
        result.innerHTML = `Your Shortly link: <a href=${shortenUrl} target="_blank">${shortenUrl}</a>`;
    } catch (error) {
        result.innerText = error.message;
    }

    function validateUrl(str) {
        str = str.trim();

        if (!/^[a-z][a-z0-9+.-]*:/i.test(str)) {
            str = `https://${str}`;
        }

        try {
            const url = new URL(str);

            const lastDotIndex = url.hostname.lastIndexOf('.');
            if (lastDotIndex <= 0 || (url.hostname.length - lastDotIndex - 1) < 2) {
                return undefined;
            }

            return url.href;
        } catch (e) {
            return undefined;
        }
    }
}