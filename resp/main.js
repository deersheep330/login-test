const axios = require('axios')
const qs = require('querystring')
const FormData = require('form-data')
const CircularJSON = require('circular-json')

const to = function(promise) {
    return promise.then(data => {
        return [null, data];
    })
    .catch(err => [err]);
}

const sleep = function(ms) {
    return new Promise(resolve => setTimeout(resolve, ms))
}

const baseUrl = 'https://app.patentcloud.com'

const defaultHeaders = {
    'referer': baseUrl + '/sep',
    'user-agent': 'Mozilla/5.0 (Windows NT 10.0; WOW64; rv:66.0) Gecko/20100101 Firefox/66.0'
}

const args = process.argv.slice(2)
const account = 'yangchiu@inquartik.com'
const password = 'Abc12345'
const claimNumber = args[0]
const decNo = args[1]
const patid = args[2]

const login = async function(account, password) {

    if (typeof login.account == 'undefined') {
        login.account = account
    }
    if (typeof login.password == 'undefined') {
        login.password = password
    }

    return new Promise( async (resolve, reject) => {

        const regexForCookie = /(?<key>[^=]+)=(?<value>[^\;]*);?\s?/g

        let url = baseUrl + '/member/login.do'

        let headers = {
            ... defaultHeaders,
            'Content-Type': 'application/x-www-form-urlencoded'
        }

        let body = {
            'account': login.account,
            'password': login.password,
            'rememberMe': 'true',
            'forceLogin[isTrusted]': 'true',
            'captcha': ''
        }

        let success = false
        while(!success) {

            const [err, res] = await to(axios({
                method: 'post',
                url: url,
                data: qs.stringify(body),
                headers: headers
            }))

            if (err) { console.log(`login error: ${err}`) }
            else {
                //console.log(res.headers['set-cookie'])
                let parsedCookie = {}
                for (let str of res.headers['set-cookie']) {
                    let matched = str.matchAll(regexForCookie)
                    for (let m of matched) {
                        let { key, value } = m.groups
                        parsedCookie[key] = value
                    }
                }
                let cookieToBeUsed = ''
                cookieToBeUsed += 't=' + parsedCookie['t'] + '; '
                cookieToBeUsed += 'v=' + parsedCookie['v'] + '; '
                cookieToBeUsed += 'SESSION=' + parsedCookie['SESSION'] + '; '
                await sleep(2000)
                resolve({
                    cookie: cookieToBeUsed
                })
                success = true
            }

            await sleep(2000)

        }

    })
}

const findClaim = async function(cookie, claimNumber, decNo, patid) {
    return new Promise( async (resolve, reject) => {

        let url = baseUrl + '/sep-api/claim/findClaimStandardRelevance.do'

        const form = new FormData()
        form.append('returnMapping', 'true')
        form.append('claimNumber', claimNumber)
        form.append('decNo', decNo)
        form.append('patid', patid)


        let headers = form.getHeaders()
        headers = {
            ... headers,
            ... defaultHeaders,
            ... cookie
        }

        const [err, res] = await to(axios({
            method: 'post',
            url: url,
            data: form,
            headers: headers
        }))

        if (err) { 
            console.log(`find claim error: ${err}`)
            reject(err)
        }
        else {
            //console.log(CircularJSON.stringify(res.data))
            resolve(res.data)
        }

    })
};

(async () => {

    const [err, res] = await to(login(account, password))
    const cookie = res

    if (err) {
        console.log(`login error: ${err}`)
    } else {
        const [err, res] = await to(findClaim(cookie, claimNumber, decNo, patid))

        if (err) {
            console.log(`find claim error: ${err}`)
        }
        else {
            
            let dict = {}

            if (res.hasOwnProperty('mapping')) {
                for (const [key, obj] of Object.entries(res.mapping)) {
                    for (const [key, _obj] of Object.entries(obj)) {
                        for (const [key, __obj] of Object.entries(_obj)) {
                            if (__obj.hasOwnProperty('expansions')) {
                                for (const [key, ___obj] of Object.entries(__obj.expansions)) {
                                    //console.log(key)
                                    for (const ____obj of ___obj) {
                                        if (____obj.hasOwnProperty('keywordType') && ____obj.hasOwnProperty('keyword') && ____obj.hasOwnProperty('keywordText')) {
                                            if (____obj.keywordType === 'abbr') {
                                                dict[____obj.keyword] = ____obj.keywordText
                                            }
                                            //console.log(____obj.keyword, ____obj.keywordText)
                                        }
                                    }
                                }
                                /*const expansions = __obj.expansions
                                console.log(expansions)
                                if (expansions.hasOwnProperty('keywordType') && expansions.hasOwnProperty('keyword') && expansions.hasOwnProperty('keywordText')) {
                                    console.log(expansions.keywordText)
                                    if (expansions.keywordType === 'abbr') {
                                        dict[expansions.keyword] = expansions.keywordText
                                    }
                                }*/
                            }
                        }
                    }
                }
            }

            console.log(JSON.stringify(dict))

        }
    }
})()
