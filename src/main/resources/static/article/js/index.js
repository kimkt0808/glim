/*
* 게시글 목록을 가져오는 함수
*
* @returns {Promise<void>}
*/
async function fetchArticles() {
    try {
        const response = await fetch('/articles', {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${ response.status }`);
        }

        const articles = await response.json();
        renderArticles(articles);
    } catch (error) {
        console.error('게시글을 가져오는 중 오류가 발생했습니다.', error);
    }
}

/*
* 게시글 목록을 렌더링하기 위한 함수
*
* @param {Array<Object>} articles - 게시글 객체들의 배열
* @param {Long} articles[].id - 게시글의 고유 ID
* @param {String} articles[].title - 게시글의 제목
* @returns {void}
*/
function renderArticles(articles) {
    const articleList = document.getElementById('board-list');
    articleList.innerHTML = ''; // 목록 초기화

    if (!articles || articles.length === 0) {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td colspan="5" class="text-center">
                게시글이 없습니다.
            </td>
        `;
        articleList.appendChild(row);
        return;
    }

    articles.forEach(article => {
        const row = document.createElement('tr');
        row.innerHTML = `
            <td>
                <a href="/articles/detail/${ article.id }" class="text-decoration-none">
                    ${ article.title || '제목 없음' }
                </a>
            </td>
        `;
        articleList.appendChild(row);
    });
}

// DOM이 완전히 로드된 후 fetchArticles 함수를 호출하도록 이벤트 리스너 추가
document.addEventListener('DOMContentLoaded', fetchArticles);