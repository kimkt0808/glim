/*
* 특정 게시글을 가져오는 함수
*
* @returns {Promise<void>}
*/
async function fetchArticles() {
    // url에서 ID만 획득
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
        renderArticles(articles);
    } catch (error) {
        console.error('게시글을 가져오는 중 오류가 발생했습니다.', error);
    }
}

/*
* 게시글 내용을 렌더링하고, 수정 및 삭제 버튼에 이벤트를 연결하는 함수
*
* @param {Object} articles - 게시글 내용
* @param {String} articles.title - 게시글 제목
* @param {String} articles.content - 게시글 본문
* @returns {void}
*/
function renderArticles(articles) {
    document.getElementById('post-title').textContent = articles.title;
    document.getElementById('post-content').textContent = articles.content;

    document.getElementById('move-update-btn').addEventListener('click', () => {
        updateArticleView(articles.id).then(r => { });
    });

    document.getElementById('delete-btn').addEventListener('click', () => {
        deleteArticle(articles.id).then(r => { });
    });
}

/*
* 게시글 수정 페이지로 이동하는 함수
*
* @returns {Promise<void>}
*/
async function updateArticleView(articleId) {
    window.location.href = `/articles/update/${ articleId }`;
}

/*
* 게시글을 삭제하는 함수
*
* @param {String} articleId - 게시글의 고유 ID
* @returns {Promise<void>}
*/
async function deleteArticle(articleId) {
    if (!confirm('정말로 이 게시글을 삭제하시겠습니까?')) {
        return ;
    }

    try {
        const response = await fetch(`/articles/${ articleId }`, {
            method: 'DELETE',
            headers: {
                'Accept': 'application/json'
            }
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${ response.status }`);
        }

        alert('게시글이 성공적으로 삭제되었습니다.');
        window.location.href = '/';
    } catch (error) {
        console.error('게시글 삭제 중 오류가 발생했습니다.', error);
        alert('게시글 삭제에 실패했습니다. 다시 시도해주세요.');
    }
}

// DOM이 완전히 로드된 후 fetchArticles 함수를 호출하도록 이벤트 리스너 추가
document.addEventListener('DOMContentLoaded', fetchArticles);