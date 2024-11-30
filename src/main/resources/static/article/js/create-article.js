/*
* 제출, 취소 버튼에 이벤트를 연결하는 함수
*
* @returns {void}
*/
function eventArticles() {
    document.getElementById('write-form').addEventListener('submit', (ev) => {
        ev.preventDefault();
        createArticle().then(r => { });
    })

    document.getElementById('cancel-btn').addEventListener('click', () => {
        window.location.href = '/';
    })
}

/*
* 게시글을 생성하는 함수
*
* @returns {Promise<void>}
*/
async function createArticle() {
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

        const response = await fetch('/articles', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(requestObject)
        });

        if (!response.ok) {
            throw new Error(`HTTP error! status: ${ response.status }`);
        }

        alert('게시글이 등록되었습니다.');
        window.location.href = '/';
    } catch (error) {
        console.error('게시글 등록 중 오류가 발생했습니다.', error);
        alert('게시글 등록에 실패했습니다. 다시 시도해주세요.');
    }
}

// DOM이 완전히 로드된 후 eventArticles 함수를 호출하도록 이벤트 리스너 추가
document.addEventListener('DOMContentLoaded', eventArticles);