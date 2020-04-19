#!/bin/sh
openapi_module="todoapp-openapi"

dir=$(PWD)
# --initでローカルの設定ファイルを初期化、updateでデータの取得と親プロジェクトで指定されているコミットにチェックアウトする。
git submodule update --init

# submoduleとして取り込んであるopenapiリポジトリにsparsecheckoutを設定する
echo "set openapi_module to sparse-checkout"
mkdir -p ./.git/modules/${openapi_module}/info
echo '/kotlin-spring' >./.git/modules/${openapi_module}/info/sparse-checkout
cd ${openapi_module}
git config core.sparsecheckout true
# sparsecheckoutの設定を反映
git read-tree -mu HEAD
cd ${dir}
