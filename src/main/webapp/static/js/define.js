! function(e) {
    "use strict";
    ! function(e) {
        function t(e) {
            e.state.placeholder && (e.state.placeholder.parentNode.removeChild(e.state.placeholder), e.state.placeholder = null)
        }

        function r(e) {
            t(e);
            var r = e.state.placeholder = document.createElement("pre");
            r.style.cssText = "height: 0; overflow: visible", r.className = "CodeMirror-placeholder", r.appendChild(document.createTextNode(e.getOption("placeholder"))), e.display.lineSpace.insertBefore(r, e.display.lineSpace.firstChild)
        }

        function a(e) {
            o(e) && r(e)
        }

        function n(e) {
            var a = e.getWrapperElement(),
                n = o(e);
            a.className = a.className.replace(" CodeMirror-empty", "") + (n ? " CodeMirror-empty" : ""), n ? r(e) : t(e)
        }

        function o(e) {
            return 1 === e.lineCount() && "" === e.getLine(0)
        }
        e.defineOption("placeholder", "", function(r, o, i) {
            var s = i && i != e.Init;
            if (o && !s) r.on("blur", a), r.on("change", n), n(r);
            else if (!o && s) {
                r.off("blur", a), r.off("change", n), t(r);
                var l = r.getWrapperElement();
                l.className = l.className.replace(" CodeMirror-empty", "")
            }
            o && !r.hasFocus() && a(r)
        })
    }(e), e.defineMode("heptaxFormula", function(e, t) {
            function r(t, r) {
                var o = t.next();
                if (p[o]) {
                    var i = p[o](t, r, e.fields);
                    if (i !== !1) return i
                }
                if (1 == m.hexNumber && ("0" == o && t.match(/^[xX][0-9a-fA-F]+/) || ("x" == o || "X" == o) && t.match(/^'[0-9a-fA-F]+'/))) return "number";
                if (1 == m.binaryNumber && (("b" == o || "B" == o) && t.match(/^'[01]+'/) || "0" == o && t.match(/^b[01]+/))) return "number";
                if (o.charCodeAt(0) > 47 && o.charCodeAt(0) < 58) return t.match(/^[0-9]*\.?[0-9]+([eE][-+]?[0-9]+)?/), 1 == m.decimallessFloat && t.eat("."), "number";
                if ("?" == o && (t.eatSpace() || t.eol() || t.eat(";"))) return "variable-3";
                if ("'" == o || '"' == o && m.doubleQuote) return r.tokenize = a(o), r.tokenize(t, r);
                if ((1 == m.nCharCast && ("n" == o || "N" == o) || 1 == m.charsetCast && "_" == o && t.match(/[a-z][a-z0-9]*/i)) && ("'" == t.peek() || '"' == t.peek())) return "keyword";
                if (/^[\(\),\;\[\]]/.test(o)) return null;
                if (m.commentSlashSlash && "/" == o && t.eat("/")) return t.skipToEnd(), "comment";
                if (m.commentHash && "#" == o || "-" == o && t.eat("-") && (!m.commentSpaceRequired || t.eat(" "))) return t.skipToEnd(), "comment";
                if ("/" == o && t.eat("*")) return r.tokenize = n, r.tokenize(t, r);
                if ("." != o) {
                    if (d.test(o)) return t.eatWhile(d), "operator";
                    if ("{" == o && (t.match(/^( )*(d|D|t|T|ts|TS)( )*'[^']*'( )*}/) || t.match(/^( )*(d|D|t|T|ts|TS)( )*"[^"]*"( )*}/))) return "number";
                    t.eatWhile(/^[_\w\d]/);
                    var h = t.current().toLowerCase();
                    return f.hasOwnProperty(h) && (t.match(/^( )+'[^']*'/) || t.match(/^( )+"[^"]*"/)) ? "number" : l.hasOwnProperty(h) ? "atom" : u.hasOwnProperty(h) ? "keyword" : c.hasOwnProperty(h) ? "builtin" : s.hasOwnProperty(h) ? "string-2" : null
                }
                return 1 == m.zerolessFloat && t.match(/^(?:\d+(?:e[+-]?\d+)?)/i) ? "number" : 1 == m.ODBCdotTable && t.match(/^[a-zA-Z_\d\u4E00-\u9FA5]+/) ? "variable-2" : void 0
            }

            function a(e) {
                return function(t, a) {
                    for (var n, o = !1; null != (n = t.next());) {
                        if (n == e && !o) {
                            a.tokenize = r;
                            break
                        }
                        o = !o && "\\" == n
                    }
                    return "string"
                }
            }

            function n(e, t) {
                for (;;) {
                    if (!e.skipTo("*")) {
                        e.skipToEnd();
                        break
                    }
                    if (e.next(), e.eat("/")) {
                        t.tokenize = r;
                        break
                    }
                }
                return "comment"
            }

            function o(e, t, r) {
                t.context = {
                    prev: t.context,
                    indent: e.indentation(),
                    col: e.column(),
                    type: r
                }
            }

            function i(e) {
                e.indent = e.context.indent, e.context = e.context.prev
            }
            var s = t.client || {},
                l = t.atoms || {
                    "false": !0,
                    "true": !0,
                    "null": !0
                },
                c = t.builtin || {},
                u = t.keywords || {},
                d = t.operatorChars || /^[\/\*\+\-%<>!=&|~^]/,
                m = t.support || {},
                p = t.hooks || {},
                f = t.dateSQL || {
                    date: !0,
                    time: !0,
                    timestamp: !0
                };
            return {
                startState: function() {
                    return {
                        tokenize: r,
                        context: null
                    }
                },
                token: function(e, t) {
                    if (e.sol() && t.context && null == t.context.align && (t.context.align = !1), e.eatSpace()) return null;
                    var r = t.tokenize(e, t);
                    if ("comment" == r) return r;
                    t.context && null == t.context.align && (t.context.align = !0);
                    var a = e.current();
                    return "(" == a ? o(e, t, ")") : "[" == a ? o(e, t, "]") : t.context && t.context.type == a && i(t), r
                },
                indent: function(t, r) {
                    var a = t.context;
                    if (!a) return 0;
                    var n = r.charAt(0) == a.type;
                    return a.align ? a.col + (n ? 0 : 1) : a.indent + (n ? 0 : e.indentUnit)
                },
                blockCommentStart: "/*",
                blockCommentEnd: "*/",
                lineComment: m.commentSlashSlash ? "//" : m.commentHash ? "#" : null
            }
        }),
        function() {
            function t(e) {
                for (var t; null != (t = e.next());)
                    if ("`" == t && !e.eat("`")) return "variable-2";
                return e.backUp(e.current().length - 1), e.eatWhile(/\w/) ? "variable-2" : null
            }

            function r(e) {
                return e.eat("@") && (e.match(/^session\./), e.match(/^local\./), e.match(/^global\./)), e.eat("'") ? (e.match(/^.*'/), "variable-2") : e.eat('"') ? (e.match(/^.*"/), "variable-2") : e.eat("`") ? (e.match(/^.*`/), "variable-2") : e.match(/^[0-9a-zA-Z$\.\_]+/) ? "variable-2" : null
            }

            function a(e, t, r) {
                for (var a, n = ""; null != (a = e.next());) {
                    if ("]" == a && !e.eat("]")) return r && angular.indexOf(r, n) < 0 ? "error" : "variable-2";
                    n += a
                }
                return null
            }

            function n(e) {
                for (var t, r = ""; null != (t = e.next());) {
                    if ("]" == t && !e.eat("]")) return "variable-2";
                    r += t
                }
                return null
            }

            function o(e) {
                return e.eat("N") ? "atom" : e.match(/^[a-zA-Z.#!?]/) ? "variable-2" : null
            }

            function i(e) {
                for (var t = {}, r = e.split(" "), a = 0; a < r.length; ++a) t[r[a]] = !0;
                return t
            }
            var s = "alter and as asc between by count create delete desc distinct drop from having in insert into is join like not on or order select set table union update values where ",
                l = "sum avg max min count distinct row_max row_min max_date min_date hour_diff minute_diff second_diff day_diff month_diff year_diff week quarter now first_day_of_month last_day_of_month work_day_of_month if year month day hour to_date date_add date_sub concat instring length repeat reverse substr day_of_week regexp_extract regexp_replace time_convert percent ip_location coalesce base64_decode base64_encode";
            // e.defineMIME("text/x-bdp-formula", {
            //     name: "bdpFormula",
            //     client: i("charset clear connect edit ego exit go help nopager notee nowarning pager print prompt quit rehash source status system tee"),
            //     keywords: i(l),
            //     atoms: i("false true null unknown"),
            //     operatorChars: /^[\/*+\-%<>!=&|^]/,
            //     dateSQL: i("date time timestamp"),
            //     support: i("ODBCdotTable decimallessFloat zerolessFloat binaryNumber hexNumber doubleQuote nCharCast charsetCast commentHash commentSpaceRequired"),
            //     hooks: {
            //         "@": r,
            //         "`": t,
            //         "\\": o,
            //         "[": a
            //     }
            // }), 
            e.defineMIME("text/x-heptax", {
                name: "heptaxFormula",
                client: i("input temp output"),
                // keywords: i(s + l + "accessible action add after algorithm all analyze asensitive at authors auto_increment autocommit avg avg_row_length before binary binlog both btree cache call cascade cascaded case catalog_name chain change changed character check checkpoint checksum class_origin client_statistics close coalesce code collate collation collations column columns comment commit committed completion concurrent condition connection consistent constraint contains continue contributors convert cross current current_date current_time current_timestamp current_user cursor data database databases day_hour day_microsecond day_minute day_second deallocate dec declare default delay_key_write delayed delimiter des_key_file describe deterministic dev_pop dev_samp deviance diagnostics directory disable discardnctrow div dual dumpfile each elseif enable enclosed end ends engine engines enum errors escape escaped even event events every execute exists exit explain extended fast fetch field fields first flush for force foreign found_rows full fulltext function general get global grant grants group groupby_concat handler hash help high_priority hosts hour_microsecond hour_minute hour_second if ignore ignore_server_ids import index index_statistics infile inner innodb inout insensitive insert_method install interval invoker isolation iterate key keys kill language last leading leave left level limit linear lines list load local localtime localtimestamp lock logs low_priority master master_heartbeat_period master_ssl_verify_server_cert masters match max max_rows maxvalue message_text middleint migrate min min_rows minute_microsecond minute_second mod mode modifies modify mutex mysql_errno natural next no no_write_to_binlog offline offset one online open optimize option optionally out outer outfile pack_keys parser partition partitions password phase plugin plugins prepare preserve prev primary privileges procedure processlist profile profiles purge query quick range read read_write reads real rebuild recover references regexp relaylog release remove rename reorganize repair repeatable replace require resignal restrict resume return returns revoke right rlike rollback rollup row row_format rtree savepoint schedule schema schema_name schemas second_microsecond security sensitive separator serializable server session share show signal slave slow smallint snapshot soname spatial specific sql sql_big_result sql_buffer_result sql_cache sql_calc_found_rows sql_no_cache sql_small_result sqlexception sqlstate sqlwarning ssl start starting starts status std stddev stddev_pop stddev_samp storage straight_join subclass_origin sum suspend table_name table_statistics tables tablespace temporary terminated to trailing transaction trigger triggers truncate uncommitted undo uninstall unique unlock upgrade usage use use_frm user user_resources user_statistics using utc_date utc_time utc_timestamp value variables varying view views warnings when while with work write xa xor year_month zerofill begin do then else loop repeat"),
                keywords:i(l),
                atoms: i("false true null unknown"),
                operatorChars: /^[*+\-%<>!=&|^]/,
                dateSQL: i("date time timestamp"),
                support: i("ODBCdotTable doubleQuote decimallessFloat zerolessFloat binaryNumber hexNumber doubleQuote nCharCast charsetCast commentHash commentSpaceRequired"),
                hooks: {
                    "@": r,
                    "`": t,
                    "\\": o,
                    "[": n
                }
            })
        }()
}(CodeMirror);