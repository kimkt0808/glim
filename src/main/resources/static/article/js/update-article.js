/*
* 게시글의 내용을 가져오는 함수
*
* @returns {Promise<void>}
*/
async function fetchArticles() {
    const pathSegments = window.location.pathname.split('/');
    const articleId = pathSegments[pathSegments.length - 1];

    try {
        const response = await fetch(`/articles/${ articleId }`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${ response.status }`);
        }

        const articles = await response.json();
        renderArticle(articles);
    } catch (error) {
        console.log('게시글을 가져오는 중 오류가 발생했습니다.', error);
    }
}

/*
* 게시글 내용을 렌더링하고, 수정 버튼에 이벤트를 연결하는 함수
*
* @param {Object} articles - 게시글 내용
* @param {String} articles.title - 게시글 제목
* @param {String} articles.content - 게시글 본문
* @returns {void}
*/
function renderArticle(articles) {
    document.getElementById('post-title').value = articles.title;
    document.getElementById('post-content').value = articles.content;

    document.getElementById('update-btn').addEventListener('click', (ev) => {
        ev.preventDefault();
        updateArticle(articles.id).then(r => { });
    });
}

/*
* 게시글을 수정하는 함수
*
* @param {String} articleId - 게시글의 고유 ID
* @returns {Promise<void>}
*/
async function updateArticle(articleId) {
    if (!confirm('정말로 수정하시겠습니까?')) {
        return ;
    }

    try {
        const title = document.getElementById('post-title').value.trim();
        const content = document.getElementById('post-content').value.trim();

        if (!title || !content) {
            alert('내용을 모두 입력해주세요.');
            return ;
        }

        const requestObject = {
            title: title,
            content: content
        };

        const response = await fetch(`/articles/${ articleId }`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestObject)
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${ response.status }`);
        }

        alert('게시글이 성공적으로 수정되었습니다.');
        window.location.href = '/';
    } catch (error) {
        console.error('게시글 수정 중 오류가 발생했습니다.', error);
        alert('게시글 수정에 실패했습니다. 다시 시도해주세요.');
    }
}

// DOM이 완전히 로드된 후 fetchArticles 함수를 호출하도록 이벤트 리스너 추가
document.addEventListener('DOMContentLoaded', fetchArticles);