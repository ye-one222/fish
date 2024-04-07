export interface fishesType {
    fishName: string,
    fileDTOList: 
        {
            originalFileName: string,
            fileSize: number,
        }[],
    fileCount: number,
    userId: null
}